# --- KONFIGURASI ---
$inputFile = "C:\Users\Akbar\scoop\apps\czkawka-gui\current\results_duplicates.txt"
$targetBaseDir = "Z:\01_anu\BakalDihapus"
$ErrorActionPreference = "Stop" # Hentikan loop jika terjadi error fatal pada file tertentu

# Buat folder tujuan jika belum ada
if (-not (Test-Path $targetBaseDir)) {
    New-Item -ItemType Directory -Path $targetBaseDir | Out-Null
    Write-Host "Folder tujuan dibuat: $targetBaseDir" -ForegroundColor Cyan
}

# Baca file
if (-not (Test-Path $inputFile)) { Write-Error "File input tidak ditemukan!"; exit }
$lines = Get-Content $inputFile

$countSuccess = 0
$countFail = 0

foreach ($line in $lines) {
    # Bersihkan whitespace
    $line = $line.Trim()

    # Parsing path dari format Czkawka ("path")
    if ($line -match '^".*"$') {
        $sourcePath = $line -replace '^"|"$', ''

        # Filter: Hanya folder _Backup atau 99_Backups
        if ($sourcePath -like "*\_Backup\*" -or $sourcePath -like "*\99_Backups\*") {
            
            # Cek apakah source benar-benar ada sebelum dipindah
            if (Test-Path -LiteralPath $sourcePath) {
                
                $fileName = Split-Path $sourcePath -Leaf
                $destPath = Join-Path $targetBaseDir $fileName

                # --- LOGIKA ANTI-BENTROK (Auto Rename) ---
                # Jika file dengan nama sama sudah ada di tujuan, tambahkan counter (misal: file_1.mkv)
                $counter = 1
                $originalDestPath = $destPath
                while (Test-Path -LiteralPath $destPath) {
                    $baseName = [System.IO.Path]::GetFileNameWithoutExtension($fileName)
                    $ext = [System.IO.Path]::GetExtension($fileName)
                    $destPath = Join-Path $targetBaseDir "$baseName`_$counter$ext"
                    $counter++
                }

                try {
                    # --- PROSES PEMINDAHAN ---
                    Write-Host "Memindahkan: $fileName" -NoNewline
                    
                    # Gunakan LiteralPath karena nama file anime Anda banyak mengandung kurung siku []
                    Move-Item -LiteralPath $sourcePath -Destination $destPath -Force

                    # --- VERIFIKASI ---
                    if (Test-Path -LiteralPath $destPath) {
                        # Cek ganda: Pastikan source sudah hilang (opsional, tapi memastikan ini move bukan copy)
                        if (-not (Test-Path -LiteralPath $sourcePath)) {
                            Write-Host " -> [OK & TERVERIFIKASI]" -ForegroundColor Green
                            $countSuccess++
                        } else {
                            Write-Host " -> [WARNING: File ada di tujuan tapi source masih ada]" -ForegroundColor Yellow
                        }
                    } else {
                        Write-Host " -> [GAGAL: File tidak ditemukan di tujuan]" -ForegroundColor Red
                        $countFail++
                    }
                }
                catch {
                    Write-Host " -> [ERROR: $($_.Exception.Message)]" -ForegroundColor Red
                    $countFail++
                }
            } else {
                Write-Host "Skip (Source tidak ditemukan): $sourcePath" -ForegroundColor DarkGray
            }
        }
    }
}

Write-Host "------------------------------------------------"
Write-Host "Selesai."
Write-Host "Sukses dipindah & terverifikasi : $countSuccess" -ForegroundColor Green
Write-Host "Gagal                           : $countFail" -ForegroundColor Red