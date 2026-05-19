$Out = "$env:USERPROFILE\Desktop\nfs_prostreet_perf_report.txt"

"=== COMPUTER INFO ===" | Out-File $Out
Get-ComputerInfo | Select-Object WindowsProductName, WindowsVersion, OsBuildNumber, OsArchitecture, CsManufacturer, CsModel, CsSystemType, CsProcessors, CsNumberOfLogicalProcessors, CsTotalPhysicalMemory, BiosVersion | Format-List | Out-File $Out -Append

"`n=== CPU ===" | Out-File $Out -Append
Get-CimInstance Win32_Processor | Select-Object Name, Manufacturer, NumberOfCores, NumberOfLogicalProcessors, MaxClockSpeed, CurrentClockSpeed | Format-List | Out-File $Out -Append

"`n=== RAM ===" | Out-File $Out -Append
Get-CimInstance Win32_PhysicalMemory | Select-Object Manufacturer, Capacity, Speed, ConfiguredClockSpeed, DeviceLocator, PartNumber | Format-Table -AutoSize | Out-File $Out -Append

"`n=== GPU ===" | Out-File $Out -Append
Get-CimInstance Win32_VideoController | Select-Object Name, AdapterRAM, DriverVersion, DriverDate, CurrentHorizontalResolution, CurrentVerticalResolution, CurrentRefreshRate, VideoProcessor, AdapterCompatibility, PNPDeviceID | Format-List | Out-File $Out -Append

"`n=== STORAGE ===" | Out-File $Out -Append
Get-PhysicalDisk | Select-Object FriendlyName, MediaType, BusType, Size, HealthStatus | Format-Table -AutoSize | Out-File $Out -Append

"`n=== VOLUMES ===" | Out-File $Out -Append
Get-Volume | Select-Object DriveLetter, FileSystemLabel, FileSystem, SizeRemaining, Size | Format-Table -AutoSize | Out-File $Out -Append

"`n=== POWER PLAN ===" | Out-File $Out -Append
powercfg /GETACTIVESCHEME | Out-File $Out -Append
powercfg /L | Out-File $Out -Append

"`n=== TOP PROCESSES BY CPU ===" | Out-File $Out -Append
Get-Process | Sort-Object CPU -Descending | Select-Object -First 15 ProcessName, Id, CPU, WorkingSet64 | Format-Table -AutoSize | Out-File $Out -Append

"`n=== TOP PROCESSES BY RAM ===" | Out-File $Out -Append
Get-Process | Sort-Object WorkingSet64 -Descending | Select-Object -First 15 ProcessName, Id, CPU, WorkingSet64 | Format-Table -AutoSize | Out-File $Out -Append

Write-Host "Report created at: $Out"
notepad $Out