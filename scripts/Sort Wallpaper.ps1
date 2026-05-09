Add-Type -AssemblyName System.Drawing

$dir = "C:\Users\Akbar\Pictures\Wallpaper"

Get-ChildItem -LiteralPath $dir -File | Where-Object { $_.Extension -match "\.(jpg|jpeg|png)$" } | ForEach-Object {
    $img = [System.Drawing.Image]::FromFile($_.FullName)
    $width = $img.Width
    $img.Dispose()

    if ($width -ge 3840) { $dest = "4K_UHD" }
    elseif ($width -ge 2560) { $dest = "2K_QHD" }
    elseif ($width -ge 1920) { $dest = "Full_HD" }
    elseif ($width -ge 1280) { $dest = "HD" }
    else { $dest = "SD" }

    $destPath = "$dir\$dest\$($_.Name)"
    
    if (Test-Path -LiteralPath $destPath) {
        $destPath = "$dir\$dest\$($_.BaseName)_$((Get-Date).Ticks)$($_.Extension)"
    }

    Move-Item -LiteralPath $_.FullName -Destination $destPath
}