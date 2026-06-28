$rcloneTera = '\\wsl$\rclone\home\akbar\rclone-extra\rclone.exe'
$drive = Get-Volume -FileSystemLabel "HDD" -ErrorAction SilentlyContinue
$hdd = if ($drive) { "$($drive.DriveLetter):\00_Backup" } else { $null }

$doc = "$env:USERPROFILE\Documents"
$pic = "$env:USERPROFILE\Pictures"
$app = "$env:APPDATA"
$music = "$env:USERPROFILE\Music"

$exclDesktop = @("--exclude", "desktop.ini", "--delete-excluded")
$exclSync = @("--exclude", ".stfolder/", "--delete-excluded")
$exclObsidian = @("--exclude", ".stfolder/", "--exclude", ".stignore", "--exclude", ".obsidian/", "--delete-excluded")
$inclPcsx2 = @("--include", "/{bios/**,gamesettings/**,memcards/**,inputprofiles/**,snaps/**,sstates/**,inis/PCSX2.ini,inis/playtime.dat}")

$filterEly = @(
    "--filter", "- **/logs/**",
    "--filter", "- **/crash-reports/**",
    "--filter", "- **/debug/**",
    "--filter", "- **/.fabric/**",
    "--filter", "- **/.cache/**",
    "--filter", "- **/telemetry/**",
    "--filter", "- **/libraries/**",
    "--filter", "- **/tacz_backup/**",
    "--filter", "- **/xaero/world-map/**",
    "--filter", "+ /elyprismlauncher.cfg",
    "--filter", "+ /accounts.json",
    "--filter", "+ /instances/**",
    "--filter", "- *",
    "--delete-excluded"
)

$teraMin = @("--min-size", "1")

czkawka_cli.exe empty-folders -d "C:\Users\Akbar\OneDrive - Institut Teknologi Sumatera" -e "C:\Users\Akbar\OneDrive - Institut Teknologi Sumatera\Vault File Tugas ITERA" -D

function Sync-Data($Method, $Name, $Exe, $Src, $Dest, [array]$ExtraArgs = @()) {
    if (-not $Dest) { return }
    Write-Host "-> [$Method] $Name" -ForegroundColor Cyan
    & $Exe $Method $Src $Dest -P @ExtraArgs
}

. "$PSScriptRoot\Cleanup-Extensions.ps1"

$folders = @(
    @{ Name = "Personal Vault";    Src = "$doc\Encrypted Sync\Personal Vault";    Method = "sync"
       Hdd = "$hdd\Personal Vault";                    Tera = "terabox:00_Personal\Personal Vault";                     Gdrive = "gdrive-akbarnetwork16:Personal Vault"
       Extras = @() }
    @{ Name = "AutoHotkey";        Src = "$doc\AutoHotkey";                        Method = "copy"
       Hdd = "$hdd\AutoHotkey";                        Tera = "terabox:AutoHotkey";                                     Gdrive = $null
       Extras = @() }
    @{ Name = "Wallpaper";         Src = "$pic\Wallpaper";                          Method = "sync"
       Hdd = "$hdd\Wallpaper";                        Tera = "terabox:00_My Laptop\Wallpaper";                         Gdrive = $null
       Extras = @() }
    @{ Name = "Screenshots";       Src = "$pic\Screenshots";                        Method = "sync"
       Hdd = "$hdd\Screenshots";                      Tera = "terabox-mylaptop-crypt:Screenshots";                     Gdrive = $null
       Extras = $exclDesktop }
    @{ Name = "Music";             Src = "$music";                                  Method = "sync"
       Hdd = "$hdd\Music";                            Tera = "terabox-mylaptop-crypt:Music";                           Gdrive = $null
       Extras = $exclDesktop }
    @{ Name = "Keyfile";           Src = "$doc\Keyfile";                            Method = "sync"
       Hdd = "$hdd\Keyfile";                          Tera = "terabox-mylaptop-crypt:Keyfile";                         Gdrive = $null
       Extras = $exclSync }
    @{ Name = "Private";           Src = "$doc\Private";                            Method = "sync"
       Hdd = "$hdd\Private";                          Tera = "terabox-mylaptop-crypt:Private";                         Gdrive = $null
       Extras = $exclSync }
    @{ Name = "PCSX2";             Src = "$doc\PCSX2";                              Method = "sync"
       Hdd = "$hdd\PCSX2";                            Tera = "terabox:Emulation\PCSX2";                                Gdrive = "gdrive-yaampun747:Config App Backup/PC/PCSX2"
       Extras = $inclPcsx2; TeraExtras = $inclPcsx2 + $teraMin }
    @{ Name = "Obsidian Vault";    Src = "$doc\Obsidian Vault";                     Method = "sync"
       Hdd = "$hdd\Obsidian Vault";                   Tera = "terabox-mylaptop-crypt:Obsidian Vault";                  Gdrive = $null
       Extras = $exclObsidian }
    @{ Name = "Czkawka";           Src = "$app\Qarmin\Czkawka\config";              Method = "sync"
       Hdd = "$hdd\Config App Backup\PC\Czkawka";     Tera = $null;                                                    Gdrive = "gdrive-yaampun747:Config App Backup/PC/Czkawka"
       Extras = @() }
    @{ Name = "Ludusavi";          Src = "$app\ludusavi";                           Method = "sync"
       Hdd = "$hdd\Config App Backup\PC\Ludusavi";    Tera = $null;                                                    Gdrive = "gdrive-yaampun747:Config App Backup/PC/Ludusavi"
       Extras = @("--include", "config.yaml*") }
    @{ Name = "Save Data Backup";  Src = "$doc\Save Data Backup";                   Method = "sync"
       Hdd = "$hdd\Save Data Backup";                 Tera = $null;                                                    Gdrive = $null
       Extras = @() }
    @{ Name = "PowerShell";        Src = "$doc\WindowsPowerShell";                  Method = "sync"
       Hdd = "$hdd\Windows PowerShell";               Tera = $null;                                                    Gdrive = $null
       Extras = @() }
    @{ Name = "ElyPrismLauncher";  Src = "$app\ElyPrismLauncher";                   Method = "sync"
       Hdd = "$hdd\Config App Backup\PC\ElyPrismLauncher";  Tera = "terabox:Config App Backup/PC/ElyPrismLauncher";  Gdrive = "gdrive-yaampun747:Config App Backup/PC/ElyPrismLauncher"
       Extras = $filterEly; TeraExtras = $filterEly + $teraMin }
)

Write-Host "=== SINKRONISASI HDD LOKAL ===" -ForegroundColor Green

if ($hdd) {
    foreach ($f in $folders) {
        if ($f.Hdd) {
            Sync-Data $f.Method "$($f.Name)" "rclone" $f.Src $f.Hdd $f.Extras
        }
    }
} else {
    Write-Host "-> Drive HDD tidak terdeteksi. Melewati...`n" -ForegroundColor Yellow
}

Write-Host "`n=== SINKRONISASI TERABOX ===" -ForegroundColor Green

$oldRcloneConfig = $env:RCLONE_CONFIG
$env:RCLONE_CONFIG = "$PSScriptRoot\rclone.conf"

foreach ($f in $folders) {
    if ($f.Tera) {
        $extra = if ($f.TeraExtras) { $f.TeraExtras } else { $f.Extras }
        Sync-Data $f.Method "$($f.Name)" $rcloneTera $f.Src $f.Tera $extra
    }
}

if ($null -eq $oldRcloneConfig) {
    Remove-Item Env:RCLONE_CONFIG -ErrorAction SilentlyContinue
} else {
    $env:RCLONE_CONFIG = $oldRcloneConfig
}

Write-Host "`n=== SINKRONISASI GOOGLE DRIVE ===" -ForegroundColor Green

foreach ($f in $folders) {
    if ($f.Gdrive) {
        Sync-Data $f.Method "$($f.Name)" "rclone" $f.Src $f.Gdrive $f.Extras
    }
}

Write-Host "`n"
Read-Host "Selesai. Tekan Enter untuk keluar"