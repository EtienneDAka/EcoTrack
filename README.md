# EcoTrack - Sistema de Gestión de Residuos Urbanos

## 📱 Descripción
EcoTrack es una aplicación Android para la gestión inteligente de residuos urbanos, utilizando estructuras de datos avanzadas (TDA) para optimizar la recolección y procesamiento de desechos.

## 🎯 Características Principales

### Gestión de Residuos
- **Cola de Recolección**: Lista circular doblemente enlazada para gestionar residuos pendientes
- **Centro de Reciclaje**: Estructura LIFO (Pila) para procesamiento de residuos
- **Registro de Residuos**: Sistema completo de clasificación por tipo y peso

### Gestión de Vehículos
- **Cola de Prioridad**: Asignación inteligente de vehículos según urgencia
- **Estado en Tiempo Real**: Monitoreo de vehículos disponibles y en ruta

### Zonas Urbanas
- **Monitoreo de Áreas**: Sistema de utilidad para identificar zonas críticas
- **Alertas Automáticas**: Notificación de zonas que requieren atención inmediata
- **Visualización Separada**: Distinción clara entre zonas críticas y normales

### Estadísticas del Sistema
- **Dashboard Visual**: Métricas clave en cards interactivas
- **Estadísticas por Tipo**: Análisis detallado de residuos por categoría
- **Resumen en Tiempo Real**: Actualización automática de datos

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java
- **Framework**: Android SDK
- **UI**: Material Design Components
- **Arquitectura**: MVC (Model-View-Controller)
- **Persistencia**: Serialización de objetos

## 📊 Estructuras de Datos Implementadas

### TDA Personalizados
1. **ListaCircularDoble**: Lista circular doblemente enlazada para gestión de residuos
2. **ColaPrioridad**: Cola con prioridad para vehículos recolectores
3. **PilaReciclaje**: Pila LIFO para el centro de reciclaje
4. **HashMap**: Gestión eficiente de zonas urbanas

## 🎨 Diseño UI/UX

### Características del Diseño
- ✅ **Responsive Design**: Adaptado para diferentes tamaños de pantalla
- ✅ **Material Design**: Componentes modernos (MaterialCardView, MaterialButton)
- ✅ **Colores Semánticos**: 
  - Verde (#4CAF50) - Centro de Reciclaje
  - Azul (#2196F3) - Estadísticas
  - Naranja/Rojo (#FF5722) - Zonas Críticas
- ✅ **Emojis Contextuales**: Mejora la comprensión visual
- ✅ **Estados Vacíos**: Mensajes informativos cuando no hay datos

## 📂 Estructura del Proyecto

```
EcotrackApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/ec/com/ecotrackapp/
│   │   │   │   ├── controller/
│   │   │   │   │   └── SistemaEcoTrack.java
│   │   │   │   ├── models/
│   │   │   │   │   ├── Residuo.java
│   │   │   │   │   ├── VehiculoRecolector.java
│   │   │   │   │   └── Zona.java
│   │   │   │   ├── tda/
│   │   │   │   │   ├── ListaCircularDoble.java
│   │   │   │   │   ├── ColaPrioridad.java
│   │   │   │   │   └── PilaReciclaje.java
│   │   │   │   ├── CentroReciclajeActivity.java
│   │   │   │   ├── EstadisticasActivity.java
│   │   │   │   ├── ZonasActivity.java
│   │   │   │   ├── ResiduosActivity.java
│   │   │   │   ├── VehiculosActivity.java
│   │   │   │   ├── RegistroResiduoActivity.java
│   │   │   │   └── MainActivity.java
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       └── values/
│   │   └── test/
│   └── build.gradle.kts
├── gradle/
├── .gitignore
├── README.md
├── REDISENO_UI.md
└── DEBUG_REPORT.md
```

## 🚀 Instalación y Configuración

### Requisitos Previos
- Android Studio Arctic Fox o superior
- JDK 11 o superior
- Android SDK API 21 o superior (Lollipop)
- Gradle 7.0+

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/EtienneDAka/EcoTrack.git
   cd EcoTrack
   ```

2. **Abrir en Android Studio**
   - File > Open
   - Seleccionar la carpeta del proyecto
   - Esperar a que Gradle sincronice

3. **Sincronizar Gradle**
   ```
   File > Sync Project with Gradle Files
   ```

4. **Ejecutar la aplicación**
   - Conectar un dispositivo Android o iniciar un emulador
   - Run > Run 'app'

## 📱 Uso de la Aplicación

### Pantalla Principal
Acceso rápido a todas las funcionalidades mediante cards interactivas.

### Registrar Residuos
1. Ingresar nombre del residuo
2. Seleccionar tipo (Plástico, Vidrio, Papel, Metal, Orgánico, etc.)
3. Especificar peso en kg
4. Seleccionar zona urbana
5. Asignar prioridad ambiental (1-10)

### Centro de Reciclaje
- **Procesar**: Mueve residuos desde la cola al centro (estructura LIFO)
- **Retirar**: Retira el último residuo procesado
- **Actualizar**: Refresca la vista

### Estadísticas
Visualiza métricas en tiempo real:
- Total de residuos
- Peso acumulado
- Vehículos disponibles/en ruta
- Zonas críticas
- Distribución por tipo de residuo

### Zonas Urbanas
- **Sección Crítica**: Zonas que requieren atención inmediata
- **Todas las Zonas**: Vista completa con indicadores de estado

## 🔧 Solución de Problemas

### Error: "Cannot resolve symbol"
**Solución**: Invalidar cache de Android Studio
```
File > Invalidate Caches / Restart > Invalidate and Restart
```

### Error de compilación Gradle
**Solución**: Limpiar y reconstruir
```
Build > Clean Project
Build > Rebuild Project
```

### Errores de IDE persistentes
Ver el archivo `DEBUG_REPORT.md` para soluciones detalladas.

## 📖 Documentación Adicional

- **REDISENO_UI.md**: Documentación completa del diseño de interfaz
- **DEBUG_REPORT.md**: Reporte de debugging y verificación de errores
- **ADAPTACION.md**: Proceso de adaptación del sistema
- **DARK_MODE.md**: Implementación de modo oscuro
- **VERIFICACION.md**: Verificación de funcionalidades

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto es parte de un trabajo académico.

## 👤 Autor

**Etienne D'Aka**
- GitHub: [@EtienneDAka](https://github.com/EtienneDAka)

## 🙏 Agradecimientos

- Material Design Components
- Android Developer Documentation
- Comunidad de Android Studio

## 📅 Versión

**Versión 1.0.0** - Noviembre 2025

### Características de esta versión:
- ✅ Sistema completo de gestión de residuos
- ✅ Estructuras de datos personalizadas (TDA)
- ✅ Interfaz optimizada para móviles
- ✅ Sistema de zonas críticas
- ✅ Estadísticas en tiempo real
- ✅ Persistencia de datos

---

**EcoTrack** - Gestión Inteligente de Residuos Urbanos 🌱♻️

