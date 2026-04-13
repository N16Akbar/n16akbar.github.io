# --- CONFIG ---
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
$remotePath = "AlistLocal:/TeraBox/Mi Note 3/Anu"

Write-Host "Sedang melakukan Deep Scan seluruh /Anu... (Mohon tunggu)" -ForegroundColor Cyan

# 1. Ambil Data
$allFilesJson = rclone lsjson "$remotePath" -R --files-only | ConvertFrom-Json

if (-not $allFilesJson) { Write-Host "Gagal mengambil data."; exit }

# 2. Pengelompokan (Size + Extension)
$groups = $allFilesJson | ForEach-Object {
    $ext = [System.IO.Path]::GetExtension($_.Name).ToLower()
    $_ | Add-Member -MemberType NoteProperty -Name "FileKey" -Value "$($_.Size)_$ext" -PassThru
} | Group-Object FileKey | Where-Object { $_.Count -gt 1 }

if ($groups.Count -eq 0) { Write-Host "Bersih! Tidak ada duplikat sama sekali." -ForegroundColor Green; exit }

# 3. Analisis Kategori Duplikat
$activeDupes = @()  # Konflik antar folder aktif
$backupDupes = @()  # Konflik dengan backup
$trashDupes  = @()  # Konflik dengan tong sampah

foreach ($g in $groups) {
    $paths = $g.Group.Path
    $hasTrash  = $paths -match "^AkanDihapus/"
    $hasBackup = $paths -match "/_Backup/"
    
    # Hitung ukuran pemborosan (Total size - 1 file master)
    $wasteSize = ($g.Count - 1) * $g.Group[0].Size

    $obj = [PSCustomObject]@{
        Files = $paths
        Size  = $g.Group[0].Size
        Waste = $wasteSize
        Count = $g.Count
    }

    if ($hasTrash) {
        $trashDupes += $obj
    } elseif ($hasBackup) {
        $backupDupes += $obj
    } else {
        # Jika tidak ada di sampah dan tidak ada di backup, berarti duplikat murni antar folder aktif
        $activeDupes += $obj
    }
}

# 4. Tampilkan Laporan
function Format-Size ($bytes) {
    if ($bytes -gt 1GB) { return "{0:N2} GB" -f ($bytes / 1GB) }
    return "{0:N2} MB" -f ($bytes / 1MB)
}

$totalActiveWaste = ($activeDupes | Measure-Object Waste -Sum).Sum
$totalBackupWaste = ($backupDupes | Measure-Object Waste -Sum).Sum
$totalTrashWaste  = ($trashDupes  | Measure-Object Waste -Sum).Sum

Write-Host "`n=== LAPORAN STATUS DUPLIKAT TERKINI ===" -ForegroundColor Yellow
Write-Host "1. DUPLIKAT AKTIF (PRIORITAS BERSIHKAN)" -ForegroundColor Red
Write-Host "   Jumlah   : $($activeDupes.Count) Kelompok"
Write-Host "   Pemborosan: $(Format-Size $totalActiveWaste)"
Write-Host "   (Ini adalah file ganda di folder Anime/Character/Ekstensi/Baru yang belum dibereskan)"

Write-Host "`n2. DUPLIKAT BACKUP" -ForegroundColor Yellow
Write-Host "   Jumlah   : $($backupDupes.Count) Kelompok"
Write-Host "   Pemborosan: $(Format-Size $totalBackupWaste)"
Write-Host "   (Aman dihapus karena sudah ada copy-nya di folder utama)"

Write-Host "`n3. DUPLIKAT DI TONG SAMPAH" -ForegroundColor Green
Write-Host "   Jumlah   : $($trashDupes.Count) Kelompok"
Write-Host "   Pemborosan: $(Format-Size $totalTrashWaste)"
Write-Host "   (Sangat Aman. Tinggal kosongkan folder AkanDihapus)"

# 5. Detail Duplikat Aktif (Jika Ada)
if ($activeDupes.Count -gt 0) {
    Write-Host "`n--- DETAIL 5 DUPLIKAT AKTIF TERBESAR ---" -ForegroundColor Cyan
    $activeDupes | Sort-Object Size -Descending | Select-Object -First 5 | ForEach-Object {
        Write-Host "Ukuran: $(Format-Size $_.Size) (x$($_.Count))" -ForegroundColor Magenta
        foreach ($p in $_.Files) { Write-Host "  -> $p" }
        Write-Host ""
    }
} else {
    Write-Host "`nSelamat! Tidak ada duplikat aktif yang tersisa." -ForegroundColor Cyan
}