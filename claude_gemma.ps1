

$env:ANTHROPIC_BASE_URL = "https://openrouter.ai/api"
$env:ANTHROPIC_AUTH_TOKEN = (Get-Content "run/.env" | Where-Object { $_ -match "^OPENROUTER_API_KEY=" } | ForEach-Object { $_.Split("=",2)[1] })
$env:ANTHROPIC_API_KEY = ""
$env:ANTHROPIC_MODEL = "google/gemma-4-26b-a4b-it"

Set-Location "C:\Users\sense\Desktop\DragonTweaks"
claude
