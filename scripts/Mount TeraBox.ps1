./"Update rclone fork.ps1"
& "C:\Portable\rclone.exe" --config "./rclone.conf" mount ":combine,upstreams='TeraBox=terabox: MyLaptopEncrypted=terabox-mylaptop-crypt: MyPhoneEncrypted=terabox-myphone-crypt:':" T: `
  --vfs-cache-mode full `
  --exclude "/TeraBox/00_Personal/Personal Vault/**" `
  --exclude "/TeraBox/00_My Laptop/Encrypted/**" `
  --exclude "/TeraBox/00_My Phone/**" `
  --network-mode `
  --volname "TeraBox" `
  --no-checksum `
  --poll-interval 0 `
  -v
# & "C:\Portable\rclone.exe" --config "./rclone.conf" mount ":combine,upstreams='TeraBox=terabox:, MyPhoneEncrypted=terabox-myphone-crypt:':" T: `