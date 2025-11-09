# Script de PowerShell para hacer push a GitHub
# EcoTrack App - Push Automático

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  EcoTrack - Git Push Setup Script" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Paso 1: Verificar estado actual
Write-Host "1. Verificando estado de Git..." -ForegroundColor Yellow
git status

# Paso 2: Agregar todos los archivos
Write-Host ""
Write-Host "2. Agregando todos los archivos al staging area..." -ForegroundColor Yellow
git add .

# Paso 3: Verificar si hay algo para commitear
Write-Host ""
Write-Host "3. Verificando archivos agregados..." -ForegroundColor Yellow
$statusOutput = git status --short
if ($statusOutput) {
    Write-Host "Archivos encontrados para commit." -ForegroundColor Green
} else {
    Write-Host "No hay cambios para commitear." -ForegroundColor Red
}

# Paso 4: Hacer commit
Write-Host ""
Write-Host "4. Creando commit inicial..." -ForegroundColor Yellow
git commit -m "Initial commit: EcoTrack App - Sistema completo de gestión de residuos con UI optimizada para móviles

Características incluidas:
- Centro de Reciclaje con estructura LIFO (Pila)
- Estadísticas del Sistema con cards visuales
- Zonas Urbanas con alertas de zonas críticas
- Gestión de Residuos con lista circular doble
- Gestión de Vehículos con cola de prioridad
- UI rediseñada con Material Design
- Layouts optimizados para dispositivos móviles
- Internacionalización con Locale
- Documentación completa (README, DEBUG_REPORT, REDISENO_UI)"

# Paso 5: Verificar el commit
Write-Host ""
Write-Host "5. Verificando commit..." -ForegroundColor Yellow
$commitCheck = git log --oneline -1 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "Commit creado exitosamente: $commitCheck" -ForegroundColor Green
} else {
    Write-Host "No se pudo crear el commit o no hay commits." -ForegroundColor Red
    Write-Host "Intentando crear un commit vacío..." -ForegroundColor Yellow
    git commit --allow-empty -m "Initial commit"
}

# Paso 6: Asegurar que estamos en la rama main
Write-Host ""
Write-Host "6. Configurando rama main..." -ForegroundColor Yellow
git branch -M main

# Paso 7: Verificar rama actual
Write-Host ""
Write-Host "7. Rama actual:" -ForegroundColor Yellow
git branch

# Paso 8: Verificar remoto
Write-Host ""
Write-Host "8. Verificando repositorio remoto..." -ForegroundColor Yellow
$remoteCheck = git remote -v
if ($remoteCheck -match "origin") {
    Write-Host "Remoto configurado:" -ForegroundColor Green
    git remote -v
} else {
    Write-Host "Configurando remoto..." -ForegroundColor Yellow
    git remote add origin https://github.com/EtienneDAka/EcoTrack.git
    Write-Host "Remoto agregado." -ForegroundColor Green
}

# Paso 9: Hacer push
Write-Host ""
Write-Host "9. Haciendo push a GitHub..." -ForegroundColor Yellow
Write-Host "NOTA: Si te pide credenciales, usa tu Personal Access Token como contraseña" -ForegroundColor Cyan
Write-Host ""

git push -u origin main

# Verificar resultado
if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "  ✓ PUSH EXITOSO!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "Tu código ha sido subido a:" -ForegroundColor Green
    Write-Host "https://github.com/EtienneDAka/EcoTrack" -ForegroundColor Cyan
    Write-Host ""
} else {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "  ✗ ERROR EN PUSH" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
    Write-Host ""
    Write-Host "Si el error es de autenticación:" -ForegroundColor Yellow
    Write-Host "1. Ve a: https://github.com/settings/tokens" -ForegroundColor White
    Write-Host "2. Generate new token (classic)" -ForegroundColor White
    Write-Host "3. Selecciona 'repo' en permisos" -ForegroundColor White
    Write-Host "4. Copia el token" -ForegroundColor White
    Write-Host "5. Úsalo como contraseña" -ForegroundColor White
    Write-Host ""
    Write-Host "Si el repositorio remoto tiene contenido, ejecuta:" -ForegroundColor Yellow
    Write-Host "git pull origin main --rebase --allow-unrelated-histories" -ForegroundColor White
    Write-Host "git push -u origin main" -ForegroundColor White
    Write-Host ""
}

