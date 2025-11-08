# Guía para hacer Push a GitHub - EcoTrack

## El error que tienes significa que necesitas hacer el commit inicial primero

Ejecuta estos comandos EN ORDEN en tu PowerShell:

## 1. Verificar el estado actual de Git
```powershell
git status
```

## 2. Agregar todos los archivos al staging area
```powershell
git add .
```

## 3. Hacer el commit inicial (si no lo has hecho)
```powershell
git commit -m "Initial commit: EcoTrack App con rediseño UI completo - Centro de Reciclaje, Estadísticas y Zonas optimizadas para móviles"
```

## 4. Verificar en qué rama estás
```powershell
git branch
```

## 5. Si la rama se llama "master", renómbrala a "main"
```powershell
git branch -M main
```

## 6. Verificar que el remoto esté configurado
```powershell
git remote -v
```

## 7. Si NO ves el remoto configurado, agrégalo:
```powershell
git remote add origin https://github.com/EtienneDAka/EcoTrack.git
```

## 8. Si el remoto YA existe pero es incorrecto, actualízalo:
```powershell
git remote set-url origin https://github.com/EtienneDAka/EcoTrack.git
```

## 9. Hacer push al repositorio
```powershell
git push -u origin main
```

## Si el repositorio remoto ya tiene contenido:

Si recibes un error de "refused to update" o "non-fast-forward", ejecuta:

```powershell
git pull origin main --allow-unrelated-histories
```

Luego resuelve cualquier conflicto si aparece, y ejecuta:

```powershell
git push -u origin main
```

## Alternativa: Forzar el push (SOLO si estás seguro)

⚠️ ADVERTENCIA: Esto sobrescribirá el contenido remoto

```powershell
git push -u origin main --force
```

## Verificación de credenciales

Si te pide credenciales, recuerda que GitHub NO acepta contraseñas.

Necesitas un Personal Access Token:
1. Ve a: https://github.com/settings/tokens
2. Generate new token (classic)
3. Selecciona "repo" en los permisos
4. Copia el token generado
5. Úsalo como contraseña cuando Git te lo pida

Usuario: EtienneDAka
Contraseña: [tu Personal Access Token]

## ✅ Después del push exitoso

Verifica en: https://github.com/EtienneDAka/EcoTrack

Deberías ver:
- ✅ Todo el código fuente
- ✅ README.md con documentación completa
- ✅ REDISENO_UI.md
- ✅ DEBUG_REPORT.md
- ✅ Layouts optimizados para móviles
- ✅ Todos los archivos Java corregidos

