# Módulo de práctica: Corrutinas en Kotlin (DSY1105)

Módulo Kotlin/JVM ("java-library") para practicar corrutinas, `sealed class` y `data class`
con una app de consola — sin Android, sin dependencias externas más allá de
`kotlinx-coroutines-core`.

## Requisitos

- IntelliJ IDEA o Android Studio
- JDK 17 o superior
- Conexión a internet (solo para que Gradle descargue la dependencia la primera vez)

## 1. Agregar las entradas al catálogo de versiones

Archivo: **`gradle/libs.versions.toml`** (va en la raíz del proyecto, no dentro del módulo).

Si el archivo ya existe (por ejemplo, porque el proyecto se creó con el wizard de Android
Studio y ya trae `agp`, `junit`, etc.), agrega solo estas líneas en su sección
correspondiente. Si es un proyecto nuevo, este es el archivo completo:

```toml
[versions]
kotlin = "2.2.10"
kotlinxCoroutinesCore = "1.11.0"

[libraries]
kotlinx-coroutines-core = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core", version.ref = "kotlinxCoroutinesCore" }

[plugins]
jetbrains-kotlin-jvm = { id = "org.jetbrains.kotlin.jvm", version.ref = "kotlin" }
```

## 2. Configurar el módulo

Archivo: **`<nombre-del-modulo>/build.gradle.kts`**

```kotlin
plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
```

## 3. Sincronizar

Después de editar ambos archivos: **Sync Now** (o el ícono del elefante 🐘 arriba a la
derecha en Android Studio). El código no compila hasta que termine la sincronización.

## Estructura del código (`Main.kt`)

| Sección | Qué hace |
|---|---|
| `data class PerfilUsuario` | Guarda los datos del usuario autenticado (nombre, email). |
| `sealed class ResultadoLogin` | Modela los 3 estados posibles del login: `Autenticando`, `Exito`, `Error`. |
| `suspend fun autenticarUsuario(...)` | Simula una verificación contra un servidor con `delay(...)`, sin bloquear el hilo. |
| `fun mostrarResultado(...)` | `when` exhaustivo sobre `ResultadoLogin`, usando la scope function `let` en el caso `Exito`. |
| `fun main()` | Usa `runBlocking` para probar la función con credenciales correctas e incorrectas. |

## Problemas comunes al configurar

- **`Unresolved reference 'kotlinx'`** → falta sincronizar Gradle, o la dependencia se agregó
  en un módulo distinto al del archivo con el `import`.
- **`Unresolved reference 'serialization'`** → falta declarar el plugin en
  `libs.versions.toml` → `[plugins]`:
  ```toml
  jetbrains-kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
  ```
  (solo hace falta si el código usa `@Serializable`/JSON — este módulo no lo necesita).
- **Entradas duplicadas tipo `...-v180`** → quedan cuando Android Studio agrega una
  dependencia por UI encima de una que ya existía. Se pueden borrar del `.toml` y del
  `build.gradle.kts` sin problema; usa siempre la entrada apuntando a la versión más nueva.