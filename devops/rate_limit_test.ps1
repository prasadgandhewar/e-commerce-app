
for ($i = 1; $i -le 50; $i++) {
    Write-Output "Request $i"
    Invoke-WebRequest -Uri "http://34.10.43.90:8400/auth/token" -Method Get
}
