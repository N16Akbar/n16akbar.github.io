./"Update rclone fork.ps1"
$rcloneextra = "C:\Portable\rclone.exe"

# ---------------------------------------------------------
# 1. LOCAL EXTERNAL HDD BACKUPS ($drivePath\00_Backup)
# ---------------------------------------------------------

$drive = Get-Volume -FileSystemLabel "HDD" -ErrorAction SilentlyContinue
$drivePath = "$($drive.DriveLetter):"

if ($null -ne $drive) {
    Write-Host "Sinkronisasi Personal Vault ke External HDD..."
    rclone sync "$env:USERPROFILE\Documents\Encrypted Sync\Personal Vault" "$drivePath\00_Backup\Personal Vault" -P

    Write-Host "Menyalin AutoHotkey ke External HDD..."
    rclone copy "$env:USERPROFILE\Documents\AutoHotkey" "$drivePath\00_Backup\AutoHotkey" -P

    Write-Host "Sinkronisasi Wallpaper ke External HDD..."
    rclone sync "$env:USERPROFILE\Pictures\Wallpaper" "$drivePath\00_Backup\Wallpaper" -P

    Write-Host "Sinkronisasi Screenshots ke External HDD..."
    rclone sync "$env:USERPROFILE\Pictures\Screenshots" "$drivePath\00_Backup\Screenshots" -P --exclude "desktop.ini" --delete-excluded

    Write-Host "Sinkronisasi Music ke External HDD..."
    rclone sync "$env:USERPROFILE\Music" "$drivePath\00_Backup\Music" -P --exclude "desktop.ini" --delete-excluded

    Write-Host "Sinkronisasi Keyfile ke External HDD..."
    rclone sync "$env:USERPROFILE\Documents\Keyfile" "$drivePath\00_Backup\Keyfile" -P --exclude ".stfolder/" --delete-excluded

    Write-Host "Sinkronisasi Private ke External HDD..."
    rclone sync "$env:USERPROFILE\Documents\Private" "$drivePath\00_Backup\Private" -P --exclude ".stfolder/" --delete-excluded

    Write-Host "Sinkronisasi PCSX2 ke External HDD..."
    rclone sync "$env:USERPROFILE\Documents\PCSX2" "$drivePath\00_Backup\PCSX2" --include '/{bios/**,gamesettings/**,memcards/**,inputprofiles/**,snaps/**,inis/PCSX2.ini,inis/playtime.dat,inis/secrets.ini}' -P

    Write-Host "Sinkronisasi Obsidian Vault ke External HDD..."
    rclone sync "$env:USERPROFILE\Documents\Obsidian Vault" "$drivePath\00_Backup\Obsidian Vault" -P --exclude ".stfolder/" --exclude ".stignore" --exclude ".obsidian/" --delete-excluded

    Write-Host "Sinkronisasi Czkawka ke External HDD..."
    rclone sync "$env:APPDATA\Qarmin\Czkawka\config" "$drivePath\00_Backup\Config App Backup\PC\Czkawka" -P

    Write-Host "Sinkronisasi Ludusavi ke External HDD..."
    rclone sync "$env:APPDATA\ludusavi" "$drivePath\00_Backup\Config App Backup\PC\Ludusavi" --include "config.yaml*" -P

    Write-Host "Sinkronisasi Save Data Backup ke External HDD..."
    rclone sync "$env:USERPROFILE\Documents\Save Data Backup" "$drivePath\00_Backup\Save Data Backup" -P

    Write-Host "Sinkronisasi ElyPrismLauncher ke External HDD..."
    rclone sync "$env:APPDATA\ElyPrismLauncher" "$drivePath\00_Backup\Config App Backup\PC\ElyPrismLauncher" `
    --min-size 1b `
    --filter "- **/logs/**" `
    --filter "- **/crash-reports/**" `
    --filter "- **/debug/**" `
    --filter "- **/.fabric/**" `
    --filter "- **/.cache/**" `
    --filter "- **/telemetry/**" `
    --filter "- **/libraries/**" `
    --filter "- **/tacz_backup/**" `
    --filter "- **/xaero/world-map/**" `
    --filter "+ /prismlauncher.cfg" `
    --filter "+ /accounts.json" `
    --filter "+ /instances/**" `
    --filter "- *" `
    --delete-excluded -P
} else {
    Write-Host "Drive HDD tidak terdeteksi."
}

# ---------------------------------------------------------
# 2. CLOUD BACKUPS (TERABOX)
# ---------------------------------------------------------

Write-Host "Sinkronisasi Personal Vault ke TeraBox..."
& $rcloneextra sync "$env:USERPROFILE\Documents\Encrypted Sync\Personal Vault" terabox:"00_Personal\Personal Vault" -P --transfers 1

Write-Host "Menyalin AutoHotkey ke TeraBox..."
& $rcloneextra copy "$env:USERPROFILE\Documents\AutoHotkey" terabox:"AutoHotkey" -P --transfers 1

Write-Host "Sinkronisasi Wallpaper ke TeraBox..."
& $rcloneextra sync "$env:USERPROFILE\Pictures\Wallpaper" terabox:"00_My Laptop\Wallpaper" -P --transfers 1

Write-Host "Sinkronisasi Screenshots ke TeraBox..."
& $rcloneextra sync "$env:USERPROFILE\Pictures\Screenshots" terabox-mylaptop-crypt:Screenshots -P --exclude "desktop.ini" --delete-excluded --transfers 1

Write-Host "Sinkronisasi Music ke TeraBox..."
& $rcloneextra sync "$env:USERPROFILE\Music" terabox-mylaptop-crypt:Music -P --exclude "desktop.ini" --delete-excluded --transfers 1

Write-Host "Sinkronisasi Keyfile ke TeraBox..."
& $rcloneextra sync "$env:USERPROFILE\Documents\Keyfile" terabox-mylaptop-crypt:Keyfile -P --exclude ".stfolder/" --delete-excluded --transfers 1

Write-Host "Sinkronisasi Private ke TeraBox..."
& $rcloneextra sync "$env:USERPROFILE\Documents\Private" terabox-mylaptop-crypt:Private -P --exclude ".stfolder/" --delete-excluded --transfers 1

Write-Host "Sinkronisasi PCSX2 ke TeraBox..."
& $rcloneextra sync "$env:USERPROFILE\Documents\PCSX2" terabox:"Emulation\PCSX2" --include '/{bios/**,gamesettings/**,memcards/**,inputprofiles/**,snaps/**,inis/PCSX2.ini,inis/playtime.dat,inis/secrets.ini}' -P

Write-Host "Sinkronisasi Obsidian Vault ke TeraBox..."
& $rcloneextra sync "$env:USERPROFILE\Documents\Obsidian Vault" "terabox-mylaptop-crypt:Obsidian Vault" -P --exclude ".stfolder/" --exclude ".stignore" --exclude ".obsidian/" --delete-excluded --transfers 1

Write-Host "Sinkronisasi ElyPrismLauncher ke TeraBox..."
& $rcloneextra sync "$env:APPDATA\ElyPrismLauncher" "terabox:Config App Backup/PC/ElyPrismLauncher" `
    --min-size 1b `
    --filter "- **/logs/**" `
    --filter "- **/crash-reports/**" `
    --filter "- **/debug/**" `
    --filter "- **/.fabric/**" `
    --filter "- **/.cache/**" `
    --filter "- **/telemetry/**" `
    --filter "- **/libraries/**" `
    --filter "- **/tacz_backup/**" `
    --filter "- **/xaero/world-map/**" `
    --filter "+ /prismlauncher.cfg" `
    --filter "+ /accounts.json" `
    --filter "+ /instances/**" `
    --filter "- *" `
    --delete-excluded -P --tpslimit 1.5

# ---------------------------------------------------------
# 3. CLOUD BACKUPS (GOOGLE DRIVE)
# ---------------------------------------------------------

Write-Host "Sinkronisasi Czkawka ke Google Drive..."
rclone sync "$env:APPDATA\Qarmin\Czkawka\config" "gdrive-yaampun747:Config App Backup/PC/Czkawka" -P --fast-list
rclone sync "$env:LOCALAPPDATA\Qarmin\Czkawka\cache" "gdrive-yaampun747:Config App Backup/PC/Czkawka" -P --fast-list

Write-Host "Sinkronisasi Personal Vault ke Google Drive..."
rclone sync "$env:USERPROFILE\Documents\Encrypted Sync\Personal Vault" gdrive-akbarnetwork16:"Personal Vault" -P --fast-list

Write-Host "Sinkronisasi Keyfile ke Google Drive..."
rclone sync "$env:USERPROFILE\Documents\Keyfile" gdrive-akbarnetwork16:"Keyfile" -P --fast-list --exclude ".stfolder/" --delete-excluded

Write-Host "Sinkronisasi Private ke Google Drive..."
rclone sync "$env:USERPROFILE\Documents\Private" gdrive-akbarnetwork16:"Private" -P --fast-list --exclude ".stfolder/" --delete-excluded

Write-Host "Sinkronisasi Obsidian Vault ke Google Drive..."
rclone sync "$env:USERPROFILE\Documents\Obsidian Vault" gdrive-akbarnetwork16:"Obsidian Vault" -P --fast-list --exclude ".stfolder/" --exclude ".stignore" --exclude ".obsidian/" --delete-excluded

Write-Host "Sinkronisasi PCSX2 ke Google Drive..."
rclone sync "$env:USERPROFILE\Documents\PCSX2" "gdrive-yaampun747:Config App Backup/PC/PCSX2" --include '/{bios/**,gamesettings/**,memcards/**,inputprofiles/**,snaps/**,inis/PCSX2.ini,inis/playtime.dat,inis/secrets.ini}' -P --fast-list

Write-Host "Sinkronisasi Ludusavi ke Google Drive..."
rclone sync "$env:APPDATA\ludusavi" "gdrive-yaampun747:Config App Backup/PC/Ludusavi" --include "config.yaml*" -P --fast-list

Write-Host "Sinkronisasi ElyPrismLauncher ke Google Drive..."
rclone sync "$env:APPDATA\ElyPrismLauncher" "gdrive-yaampun747:Config App Backup/PC/ElyPrismLauncher" `
    --min-size 1b `
    --filter "- **/logs/**" `
    --filter "- **/crash-reports/**" `
    --filter "- **/debug/**" `
    --filter "- **/.fabric/**" `
    --filter "- **/.cache/**" `
    --filter "- **/telemetry/**" `
    --filter "- **/libraries/**" `
    --filter "- **/tacz_backup/**" `
    --filter "- **/xaero/world-map/**" `
    --filter "+ /prismlauncher.cfg" `
    --filter "+ /accounts.json" `
    --filter "+ /instances/**" `
    --filter "- *" `
    --delete-excluded -P --fast-list

Read-Host "Selesai. Tekan Enter untuk keluar"