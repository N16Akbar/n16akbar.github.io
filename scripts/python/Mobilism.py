#!/usr/bin/env python3
import os
import re
import random
import time
from html import unescape
from urllib.request import Request, urlopen
from urllib.error import HTTPError, URLError
from urllib.parse import urlencode, urljoin, urlparse, parse_qs

BASE_URL = "https://forum.mobilism.org/"
COOKIE_FILE = "mobilism_cookie.txt"

MAX_DYNAMIC_PAGES = 2 

APP_AUTHOR_PAIRS = [
    ("Cryptomator", "derrin"),
    ("Battery Guru", "balatan"),
    ("Camscanner", "youarefinished"),
    ("YouTube Revanced", "youarefinished"),
    ("YouTube Music Revanced", "youarefinished"),
]

def dynamic_delay(min_sec=2.0, max_sec=4.5):
    time.sleep(random.uniform(min_sec, max_sec))

def load_cookie():
    if not os.path.exists(COOKIE_FILE):
        raise RuntimeError(f"File {COOKIE_FILE} tidak ditemukan. Buat dan isi dengan Cookie login Anda.")
    with open(COOKIE_FILE, "r", encoding="utf-8") as f:
        cookie = f.read().strip()
    return cookie

def clean_text(text):
    if text is None: return ""
    for _ in range(5):
        new = unescape(text)
        if new == text: break
        text = new
    return text

def is_bad_page(html):
    text = clean_text(html).lower()
    if "not permitted to use the search system" in text: return "Limit pencarian tercapai"
    if "sorry but you are not permitted" in text: return "Akses ditolak"
    if "please login" in text or "ucp.php?mode=login" in text: return "Cookie expired / Butuh login ulang"
    if "error code 522" in text: return "Cloudflare 522 (Timeout)"
    return None

def fetch(url, cookie, referer=BASE_URL, retries=3):
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/148.0.0.0 Safari/537.36",
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        "Accept-Language": "en-US,en;q=0.9",
        "Referer": referer,
        "Connection": "keep-alive",
        "Cookie": cookie,
    }

    last_error = None
    for attempt in range(1, retries + 1):
        try:
            req = Request(url, headers=headers)
            with urlopen(req, timeout=60) as r:
                charset = r.headers.get_content_charset() or "utf-8"
                return r.read().decode(charset, errors="replace")
        except HTTPError as e:
            last_error = e
            if e.code in (403, 429, 500, 502, 503, 504, 520, 521, 522, 524):
                dynamic_delay(attempt * 4.0, attempt * 8.0) 
                continue
            raise
        except URLError as e:
            last_error = e
            dynamic_delay(attempt * 4.0, attempt * 8.0)
    raise last_error

def build_search_url(keyword, author, start=0):
    params = {
        "keywords": keyword, "terms": "all", "author": author,
        "sc": "1", "sf": "titleonly", "sr": "topics",
        "sk": "t", "sd": "d", "st": "0", "ch": "300", "t": "0", "submit": "Search",
    }
    if start: params["start"] = str(start)
    return BASE_URL + "search.php?" + urlencode(params)

def parse_attrs(attrs_raw):
    attr_pattern = re.compile(r"([:\w-]+)\s*=\s*(['\"])(.*?)\2", re.I | re.S)
    attrs = {}
    for m in attr_pattern.finditer(attrs_raw):
        attrs[m.group(1).lower()] = clean_text(m.group(3))
    return attrs

def extract_topics(html, page_url):
    html = clean_text(html)
    anchor_pattern = re.compile(r"<a\b(?P<attrs>[^>]*)>(?P<body>.*?)</a>", re.I | re.S)
    topics = []
    
    for match in anchor_pattern.finditer(html):
        attrs = parse_attrs(match.group("attrs"))
        href = attrs.get("href", "").strip()
        class_name = attrs.get("class", "").strip()
        
        if "topictitle" not in class_name or "viewtopic.php" not in href:
            continue
            
        title = re.sub(r"<[^>]+>", " ", match.group("body"))
        title = " ".join(clean_text(title).split())

        full_url = urljoin(page_url, href)
        parsed = urlparse(full_url)
        qs = parse_qs(parsed.query)
        topic_id = qs.get("t", [""])[0]
        
        if not topic_id: continue
        topics.append({"title": title, "url": full_url, "id": topic_id})
        
    return topics

def extract_version_from_title(title):
    m = re.search(r"\bv\s*([0-9]+(?:\.[0-9]+)+)", title, re.I)
    if not m: return None
    try:
        return tuple(int(x) for x in m.group(1).split("."))
    except ValueError:
        return (0,)

def parse_post_context(html):
    parts = re.split(r'<div[^>]*class="content"[^>]*>', html, maxsplit=2, flags=re.I)
    if len(parts) < 2: return ""
        
    raw = parts[1]
    
    for delimiter in ['<div id="sig', '<div class="back2top"', '<div class="notice"', '<dl class="postprofile"']:
        raw = re.split(delimiter, raw, maxsplit=1, flags=re.I)[0]
        
    raw = re.sub(r'<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>', '', raw, flags=re.I)
    raw = re.sub(r'<style\b[^<]*(?:(?!<\/style>)<[^<]*)*<\/style>', '', raw, flags=re.I)
    
    raw = re.sub(r'<br\s*/?>', '\n', raw, flags=re.I)
    raw = re.sub(r'</p>|</div>|</li>|</tr>|<hr\s*/?>', '\n', raw, flags=re.I)
    
    def link_replacer(match):
        url = unescape(match.group(1))
        text = match.group(2)
        url_lower = url.lower()
        
        # Buang link internal Mobilism
        if "forum.mobilism.org" in url_lower: 
            return "" 
            
        # Ambil semua link eksternal tanpa whitelist
        if url_lower.startswith('http'):
            return f"\n{url}\n"
            
        return text 

    raw = re.sub(r'<a\b[^>]*href="([^"]+)"[^>]*>(.*?)</a>', link_replacer, raw, flags=re.I | re.S)
    
    text = re.sub(r'<[^>]+>', '', raw)
    text = clean_text(text)
    
    text = re.sub(r'^[ \t]+', '', text, flags=re.M)
    text = re.sub(r'\n[ \t]+\n', '\n\n', text)
    text = re.sub(r'\n{3,}', '\n\n', text)
    
    lower_text = text.lower()
    for marker in ["download instructions:", "download links:", "links:"]:
        idx = lower_text.find(marker)
        if idx != -1:
            text = text[idx:]
            break
            
    lower_text = text.lower()
    for footer_marker in ["trouble downloading? read", "trouble downloading?", "thank you\n", "thank you \n"]:
        idx = lower_text.find(footer_marker)
        if idx != -1:
            text = text[:idx]
            lower_text = lower_text[:idx]

    return text.strip()

def group_and_format_links(raw_text):
    """
    Algoritma pengelompokan dinamis tanpa batas whitelist.
    """
    lines = [line.strip() for line in raw_text.split('\n') if line.strip()]
    noise = ["download instructions:", "download links:", "links:"]
    lines = [l for l in lines if l.lower() not in noise]
    
    groups = []
    current_title_parts = []
    current_main = []
    current_mirrors = []
    state = "TITLE"
    
    for line in lines:
        is_url = line.startswith('http://') or line.startswith('https://')
        is_mirror_keyword = line.lower() in ['mirror:', 'mirrors:', 'mirror']
        
        if not is_url:
            if is_mirror_keyword:
                state = "MIRROR"
            else:
                if state in ["MAIN", "MIRROR"]:
                    groups.append({
                        "title": current_title_parts,
                        "main": current_main,
                        "mirrors": current_mirrors
                    })
                    current_title_parts = [line]
                    current_main = []
                    current_mirrors = []
                    state = "TITLE"
                else:
                    current_title_parts.append(line)
        else: 
            if state == "TITLE" or state == "MAIN":
                current_main.append(line)
                state = "MAIN"
            elif state == "MIRROR":
                current_mirrors.append(line)
                
    if current_main or current_mirrors:
        groups.append({"title": current_title_parts, "main": current_main, "mirrors": current_mirrors})
        
    final_groups = []
    prev_title = []
    
    for g in groups:
        curr_title = g["title"]
        do_inherit = False
        
        if prev_title and curr_title:
            is_all_caps = curr_title[0].isupper() and len(curr_title[0]) > 3
            has_version = re.search(r'\bv\d+', curr_title[0], re.I)
            
            if not is_all_caps and not has_version and len(curr_title) <= len(prev_title):
                do_inherit = True
        
        if do_inherit:
            merged_title = prev_title[:len(prev_title) - len(curr_title)] + curr_title
        else:
            merged_title = curr_title
            
        prev_title = merged_title
        title_str = " ❯ ".join(merged_title) if merged_title else "Main Package"
        
        final_groups.append({
            "title": title_str,
            "main": g["main"],
            "mirrors": g["mirrors"]
        })
        
    # Render Output murni tanpa emoji
    output = []
    for g in final_groups:
        output.append(f"[ {g['title']} ]")
        for url in g['main']:
            output.append(f"   -> Link   : {url}")
        for url in g['mirrors']:
            output.append(f"   -> Mirror : {url}")
        output.append("") 
        
    return "\n".join(output).strip()


def search_latest_app_topic(keyword, author, cookie):
    all_topics = []
    
    for page in range(MAX_DYNAMIC_PAGES):
        start = page * 40
        url = build_search_url(keyword, author, start)
        
        try:
            html = fetch(url, cookie)
            bad = is_bad_page(html)
            if bad:
                print(f"[!] Ditolak Mobilism: {bad} ({keyword})")
                break
                
            page_topics = extract_topics(html, url)
            if not page_topics: break
                
            all_topics.extend(page_topics)
            
            if page < MAX_DYNAMIC_PAGES - 1:
                dynamic_delay(1.5, 3.5)
                
        except Exception as e:
            print(f"[!] Error saat mengambil {keyword}: {e}")
            break

    unique_topics = list({t["id"]: t for t in all_topics}.values())
    unique_topics.sort(key=lambda t: extract_version_from_title(t["title"]) or (0,), reverse=True)
    return unique_topics

def main():
    try:
        cookie = load_cookie()
    except Exception as e:
        print(str(e))
        return

    print("=" * 60)
    for keyword, author in APP_AUTHOR_PAIRS:
        topics = search_latest_app_topic(keyword, author, cookie)
        
        if not topics:
            print(f"[!] Topik tidak ditemukan untuk: {keyword}")
            print("=" * 60)
            continue
            
        latest_topic = topics[0]
        
        try:
            dynamic_delay(2.0, 4.5) 
            topic_html = fetch(latest_topic["url"], cookie)
            bad = is_bad_page(topic_html)
            
            if bad:
                print(f"[{keyword} - {latest_topic['title']}]\n[!] Gagal memuat konten topik: {bad}\n")
                print("=" * 60)
                continue

            extracted_text = parse_post_context(topic_html)
            
            print(f"[{keyword} - {latest_topic['title']}]\n")
            if extracted_text:
                formatted_output = group_and_format_links(extracted_text)
                print(formatted_output)
            else:
                print("(Postingan kosong atau parser gagal mengekstrak)")
            print("\n" + "=" * 60)
            
        except Exception as e:
            print(f"[!] Gagal mengekstrak topik {keyword}: {e}")
            print("\n" + "=" * 60)

if __name__ == "__main__":
    main()