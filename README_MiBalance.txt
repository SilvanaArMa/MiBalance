# MiBalance - proyecto Android

Aplicación académica de control financiero desarrollada con Kotlin, Jetpack Compose y Room.

## Funciones incluidas
- Bienvenida e inicio.
- Registro, edición y eliminación de categorías y movimientos.
- Presupuestos por categoría (crear, editar, consultar y eliminar).
- Metas de ahorro (crear, editar, consultar y eliminar).
- Reporte de ingresos, gastos y saldo calculado a partir de los movimientos.
- Pantalla de configuración de preferencias de sesión.

## Arquitectura
- `data/database/entity`: cuatro entidades Room: Categoria, Movimiento, Presupuesto y MetaAhorro.
- `data/database/dao`: DAO para las cuatro tablas.
- `data/mapper`: conversión Entity <-> modelo de dominio.
- `data/repository`: implementación del repositorio.
- `data/di`: módulos de Hilt.
- `domain/model` y `domain/repository`: modelos e interfaz de repositorio.
- `ui/viewmodel`: ViewModel con StateFlow y operaciones.
- `ui`: pantallas Compose.

## Patrones
- Inyección de dependencias: Hilt (`@HiltAndroidApp`, `@AndroidEntryPoint`, módulos `@InstallIn`).
- Repository: `MiBalanceRepository` y `MiBalanceRepositoryImpl`.
- Singleton: proveedor `@Singleton` de Room en Hilt.
- Observer: Room Flow -> StateFlow -> `collectAsStateWithLifecycle`.
- UDF: las pantallas observan estado del ViewModel y envían eventos mediante funciones.

## Abrir el proyecto
1. Descomprime el ZIP.
2. Abre la carpeta `MiBalance` desde Android Studio.
3. Permite Gradle Sync. La primera sincronización requiere internet para descargar Gradle y las dependencias de Hilt/AndroidX si no están en caché.
4. Ejecuta `Build > Make Project`.
5. Inicia un AVD Android y presiona Run.

## Nota sobre Room
Se agregó una migración de versión 1 a 2 para crear las tablas de Presupuestos y Metas de ahorro sin borrar las categorías y movimientos existentes.

## Importante
Este paquete contiene el código fuente y configuración del proyecto. En este entorno no fue posible ejecutar una compilación Android porque el wrapper necesita descargar Gradle y no había conexión de red. Por eso, después de abrirlo en tu Android Studio, realiza Gradle Sync y Make Project; si aparece un error, conserva el texto completo para corregirlo.
