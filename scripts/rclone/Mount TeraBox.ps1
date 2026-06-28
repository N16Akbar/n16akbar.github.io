& "$PSScriptRoot\Update rclone fork.ps1"
# & "C:/Portable/rclone.exe" --config "./rclone.conf" mount ":combine,upstreams='TeraBox=terabox: MyLaptopEncrypted=terabox-mylaptop-crypt: MyPhoneEncrypted=terabox-myphone-crypt:':" T: `
& '\\wsl$\rclone\home\akbar\rclone-extra\rclone.exe' --config "$PSScriptRoot\rclone.conf" mount ":combine,upstreams='TeraBox=terabox: MyLaptopEncrypted=terabox-mylaptop-crypt: MyPhoneEncrypted=terabox-myphone-crypt:':" T: `
  --vfs-cache-mode full `
  --vfs-cache-max-age 168h `
  --exclude "/TeraBox/00_Personal/Personal Vault/**" `
  --exclude "/TeraBox/00_My Laptop/Encrypted/**" `
  --exclude "/TeraBox/00_My Phone/**" `
  --network-mode `
  --volname "TeraBox" `
  --poll-interval 0
# & "C:\Portable\rclone.exe" --config "./rclone.conf" mount ":combine,upstreams='TeraBox=terabox:, MyPhoneEncrypted=terabox-myphone-crypt:':" T: `