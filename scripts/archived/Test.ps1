Write-Host "Scanning for apps with custom notification sounds..." -ForegroundColor Cyan

# 1. Ambil daftar aplikasi pihak ketiga (user-installed)
$userApps = (adb shell pm list packages -3) -replace "package:", "" -replace "`r", ""

# 2. Ambil data notifikasi dari sistem
$dump = adb shell dumpsys notification

$results = @()
$currentApp = ""

# 3. Proses data baris demi baris
foreach ($line in $dump) {
    if ($line -match "AppSettings:\s([^\s()]+)") {
        $currentApp = $matches[1]
    }
    elseif ($currentApp -in $userApps -and $line -match "NotificationChannel\{" -and $line -match "mSound=(android\.resource[^\s,]+)") {
        $sound = $matches[1]
        $channel = if ($line -match "mId='([^']+)'") { $matches[1] } else { "Unknown" }

        $results += [PSCustomObject]@{
            App        = $currentApp
            Channel    = $channel
            SoundValue = $sound
        }
    }
}

# 4. Ambil daftar aplikasi unik agar tidak ditanya berulang kali untuk app yang sama
$uniqueApps = $results | Select-Object -ExpandProperty App -Unique

if ($uniqueApps.Count -eq 0) {
    Write-Host "No apps found using custom resource notification sounds." -ForegroundColor Green
    exit
}

Write-Host "`nFound $($uniqueApps.Count) apps with custom sounds. Let's review them:`n" -ForegroundColor Yellow

# 5. Tanya pengguna untuk setiap aplikasi
foreach ($app in $uniqueApps) {
    $response = Read-Host "Do you want to clear data and reset channels for '$app'? (y/n)"
    
    if ($response -match "^y$|^yes$") {
        Write-Host "  -> Clearing data for: $app..." -ForegroundColor Cyan
        adb shell pm clear $app
        adb shell am force-stop $app
        Write-Host "  -> Done.`n" -ForegroundColor Green
    }
    else {
        Write-Host "  -> Skipping $app.`n" -ForegroundColor DarkGray
    }
}

Write-Host "Process complete! Remember to open the cleared apps so they can regenerate their notification channels." -ForegroundColor Green