$extRemote = "gdrive-yaampun747:Extension Backup"

$extFilesRaw = rclone lsjson "$extRemote" --files-only | ConvertFrom-Json
$extFiles = [System.Collections.Generic.List[object]]::new()

if ($null -ne $extFilesRaw) {
    foreach ($entry in $extFilesRaw) {
        if ($entry -is [System.Array]) {
            foreach ($subEntry in $entry) {
                if ($null -ne $subEntry) {
                    $extFiles.Add($subEntry)
                }
            }
        } else {
            $extFiles.Add($entry)
        }
    }
}

if ($extFiles.Count -gt 0) {
    Write-Host "-> Membersihkan Extension Backup lama..." -ForegroundColor Cyan

    $allParsed = [System.Collections.Generic.List[psobject]]::new()
    $latestFiles = [System.Collections.Generic.Dictionary[string, psobject]]::new()

    foreach ($file in $extFiles) {
        $name = [string]$file.Name
        $path = [string]$file.Path

        if ($file.ModTime -is [datetime]) {
            $modTime = $file.ModTime
        } else {
            $modTime = [datetime]::Parse(
                [string]$file.ModTime,
                [System.Globalization.CultureInfo]::InvariantCulture,
                [System.Globalization.DateTimeStyles]::RoundtripKind
            )
        }

        $group = $name -replace ' \(\d+\)(?=\.[^.]+$)|\.[^.]+$|-export-\d{4}\.\d{2}\.\d{2}-chrome$|[_-]\d{4}[-.]\d{2}[-.]\d{2}(_\d{2}\.\d{2}\.\d{2})?$', ''
        $isDup = if ($name -match ' \(\d+\)(?=\.[^.]+$)') { 1 } else { 0 }

        $dateMatch = [regex]::Match($name, '(\d{4})[-.](\d{2})[-.](\d{2})(?:_(\d{2})\.(\d{2})\.(\d{2}))?')

        if ($dateMatch.Success) {
            $hr = if ($dateMatch.Groups[4].Success) { [int]$dateMatch.Groups[4].Value } else { 0 }
            $mn = if ($dateMatch.Groups[5].Success) { [int]$dateMatch.Groups[5].Value } else { 0 }
            $sc = if ($dateMatch.Groups[6].Success) { [int]$dateMatch.Groups[6].Value } else { 0 }

            $time = [datetime]::new(
                [int]$dateMatch.Groups[1].Value,
                [int]$dateMatch.Groups[2].Value,
                [int]$dateMatch.Groups[3].Value,
                $hr,
                $mn,
                $sc
            )
        } else {
            $time = $modTime
        }

        $parsedObj = [PSCustomObject]@{
            Group      = $group
            Path       = $path
            BackupTime = $time
            ModTime    = $modTime
            IsDup      = $isDup
        }

        $allParsed.Add($parsedObj)

        if ($latestFiles.ContainsKey($group)) {
            $currentLatest = $latestFiles[$group]
            $replace = $false

            if ($parsedObj.BackupTime -gt $currentLatest.BackupTime) {
                $replace = $true
            } elseif ($parsedObj.BackupTime -eq $currentLatest.BackupTime) {
                if ($parsedObj.IsDup -lt $currentLatest.IsDup) {
                    $replace = $true
                } elseif ($parsedObj.IsDup -eq $currentLatest.IsDup) {
                    if ($parsedObj.ModTime -gt $currentLatest.ModTime) {
                        $replace = $true
                    }
                }
            }

            if ($replace) {
                $latestFiles[$group] = $parsedObj
            }
        } else {
            $latestFiles[$group] = $parsedObj
        }
    }

    $keepPaths = [System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase)

    foreach ($value in $latestFiles.Values) {
        [void]$keepPaths.Add($value.Path)
    }

    foreach ($item in $allParsed) {
        if (-not $keepPaths.Contains($item.Path)) {
            rclone deletefile "$extRemote/$($item.Path)"
        }
    }

    Write-Host "-> Selesai. Hanya file Extension Backup terbaru yang tersisa.`n"
}
