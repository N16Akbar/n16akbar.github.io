$bin = "C:\Portable\rclone.exe"
$repo = "gulp79/rclone-extra"
$tag = (Invoke-RestMethod "https://api.github.com/repos/$repo/releases/latest").tag_name

if (!(Test-Path $bin) -or (& $bin version)[0] -notmatch $tag) {
    Stop-Process -Name "rclone" -Force -ErrorAction Ignore
    Invoke-WebRequest "https://github.com/$repo/releases/latest/download/rclone-windows-amd64.zip" -OutFile "$env:TEMP\r.zip"
    Expand-Archive "$env:TEMP\r.zip" "$env:TEMP\r" -Force
    Copy-Item (Get-ChildItem "$env:TEMP\r\rclone.exe" -Recurse).FullName $bin -Force
    Remove-Item "$env:TEMP\r.zip", "$env:TEMP\r" -Recurse -Force
}