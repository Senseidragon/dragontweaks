# generate_stubs.ps1
# Run from project root: .\generate_stubs.ps1

$projectRoot = $PSScriptRoot
$outputDir = Join-Path $projectRoot "docs\stubs"
$jars = @(
    @{ path = "libs\minecolonies-1.1.1299-1.21.1-snapshot.jar"; filter = "com.minecolonies.api." },
    @{ path = "build\moddev\artifacts\neoforge-21.1.226-merged.jar"; filter = "net.neoforged.neoforge." }
)

# Clean and recreate output dir
if (Test-Path $outputDir) {
    Remove-Item -Recurse -Force $outputDir
}
New-Item -ItemType Directory -Force -Path $outputDir | Out-Null

foreach ($entry in $jars) {
    $jarPath = Join-Path $projectRoot $entry.path
    $filter = $entry.filter

    if (-not (Test-Path $jarPath)) {
        Write-Warning "JAR not found: $jarPath"
        continue
    }

    Write-Host "Processing $($entry.path) (filter: $filter)..."

    $classes = & jar tf $jarPath |
        Where-Object { 
            $_ -match '\.class$' -and 
            $_ -notmatch '\$' -and
            ($_ -replace '/', '.') -match "^$([regex]::Escape($filter))"
        } |
        ForEach-Object { $_ -replace '/', '.' -replace '\.class$', '' }

    Write-Host "Found $($classes.Count) matching classes."

    foreach ($class in $classes) {
        $relativePath = $class -replace '\.', '\'
        $outFile = Join-Path $outputDir "$relativePath.java"
        $outFolder = Split-Path $outFile -Parent

        New-Item -ItemType Directory -Force -Path $outFolder | Out-Null

        & javap -public -cp $jarPath $class 2>$null | Out-File -FilePath $outFile -Encoding utf8
    }

    Write-Host "Done: $($entry.path)"
}

Write-Host "All stubs written to $outputDir"
