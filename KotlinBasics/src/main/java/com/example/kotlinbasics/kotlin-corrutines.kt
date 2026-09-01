package com.example.kotlinbasics

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// ---------------------------------------------------------
// 1. MODELADO DE DATOS
// ---------------------------------------------------------

// data class: ideal para guardar datos simples, ya que Kotlin genera
// automáticamente toString(), equals()/hashCode() y copy().
data class PerfilUsuario(
    val nombreUsuario: String,
    val email: String
)

// ---------------------------------------------------------
// 2. MODELADO DE ESTADOS (sealed class)
// ---------------------------------------------------------

// sealed class: representa un conjunto FIJO y CERRADO de resultados posibles
// para el login. El compilador conoce todos los subtipos, por eso el "when"
// que la usa más abajo no necesita una rama "else".
sealed class ResultadoLogin {

    // No necesita datos propios -> se declara como "object" (un único
    // singleton; no se pueden crear instancias nuevas de Autenticando).
    object Autenticando : ResultadoLogin()

    // Sí necesita llevar datos (el perfil obtenido) -> data class.
    data class Exito(val perfil: PerfilUsuario) : ResultadoLogin()

    // También lleva datos propios: el motivo de la falla.
    data class Error(val mensaje: String) : ResultadoLogin()
}

// ---------------------------------------------------------
// 3. FUNCIÓN ASÍNCRONA (suspend fun)
// ---------------------------------------------------------

// "suspend" marca esta función como pausable: puede "ceder" el hilo mientras
// espera (en la vida real, una respuesta de red) y retomar la ejecución
// después, sin bloquear el programa.
suspend fun autenticarUsuario(usuario: String, contrasena: String): ResultadoLogin {

    // delay() es la versión "suspend" de un Thread.sleep(): pausa la
    // corrutina 2000 ms (2 segundos) SIN bloquear el hilo en el que corre.
    delay(2000L)

    // Lógica de validación simple pedida por la guía.
    return if (usuario == "admin" && contrasena == "1234") {
        // Login correcto -> se arma un perfil de ejemplo y se envuelve en Exito.
        ResultadoLogin.Exito(
            PerfilUsuario(nombreUsuario = "admin", email = "admin@duocuc.cl")
        )
    } else {
        // Cualquier otra combinación de credenciales -> Error con mensaje.
        ResultadoLogin.Error("Credenciales incorrectas")
    }
}

// ---------------------------------------------------------
// 4. MANEJO DEL RESULTADO (when + scope function let)
// ---------------------------------------------------------

// Se separa en una función aparte para no repetir el mismo "when" dos veces
// (la guía pide probar con credenciales correctas e incorrectas).
fun mostrarResultado(resultado: ResultadoLogin) {
    when (resultado) {

        // Como ResultadoLogin es "sealed", Kotlin sabe que estos son TODOS
        // los casos posibles -> no hace falta un "else".
        is ResultadoLogin.Autenticando ->
            println("Estado: autenticando... por favor espera.")

        is ResultadoLogin.Exito ->
            // Función de ámbito "let": tomamos el "perfil" que viene dentro
            // de Exito y operamos sobre él usando "it", de forma concisa.
            // (Si perfil fuera nullable, let también evitaría el bloque si fuese null).
            resultado.perfil.let {
                println("Login exitoso. Bienvenido/a ${it.nombreUsuario} (${it.email})")
            }

        is ResultadoLogin.Error ->
            println("Error de autenticación: ${resultado.mensaje}")
    }
}

// ---------------------------------------------------------
// 5. PUNTO DE ENTRADA (main)
// ---------------------------------------------------------

fun main() {

    // runBlocking crea un CoroutineScope y "puentea" el mundo normal (main,
    // que no es suspend) con el mundo de las corrutinas. Bloquea el hilo
    // principal SOLO mientras dura este bloque, esperando a que termine.
    runBlocking {

        // --- Intento 1: credenciales correctas ---
        println("----- Intento de login 1 -----")
        mostrarResultado(ResultadoLogin.Autenticando) // estado inicial, antes de llamar a la función
        val resultado1 = autenticarUsuario("admin", "1234") // se pausa 2s aquí, sin bloquear el hilo
        mostrarResultado(resultado1)

        // --- Intento 2: credenciales incorrectas ---
        println("\n----- Intento de login 2 -----")
        mostrarResultado(ResultadoLogin.Autenticando)
        val resultado2 = autenticarUsuario("usuario", "0000")
        mostrarResultado(resultado2)
    }
}