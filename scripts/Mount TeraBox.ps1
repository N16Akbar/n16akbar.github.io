./"Update rclone fork.ps1"
& "C:\Portable\rclone.exe" mount ":combine,upstreams='TeraBox=terabox: MyLaptopEncrypted=terabox-mylaptop-crypt:':" T: `
  --vfs-cache-mode full `
  --exclude "/TeraBox/00_Personal/Personal Vault/**" `
  --exclude "/TeraBox/00_My Laptop/Encrypted/**" `
  --exclude "/TeraBox/00_My Phone/**" `
  --network-mode `
  --volname "TeraBox" `
  --no-checksum `
  --poll-interval 0 `
  -v