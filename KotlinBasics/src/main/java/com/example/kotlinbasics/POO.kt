package com.example.kotlinbasics

open class Persona(
    val nombre: String,
    val edad: Int
) {

    open fun presentarse() {
        println("Hola, mi nombre es $nombre.")
    }
}

class Empleado(
    nombre: String,
    edad: Int,
    val puesto: String,
    private val salario: Double
) : Persona(nombre, edad) {

    fun mostrarPuesto() {
        println("$nombre trabaja como $puesto.")
    }

    override fun presentarse() {
        println("Hola, soy $nombre y mi puesto es $puesto.")
    }
}

fun main() {

    // Creación de una Persona
    val persona = Persona("Juan", 25)

    // Creación de un Empleado
    val empleado = Empleado(
        "Pedro",
        30,
        "Desarrollador",
        1500000.0
    )

    // Llamamos al método presentarse() de Persona
    persona.presentarse()

    // Llamamos al método presentarse() sobrescrito por Empleado
    empleado.presentarse()

    // Mostramos el puesto del empleado
    empleado.mostrarPuesto()

    /*
     * La misma llamada al método presentarse() genera resultados
     * diferentes porque cada clase tiene una implementación distinta.
     *
     * En Persona, presentarse() muestra el nombre de la persona.
     * En Empleado, presentarse() muestra además el puesto.
     *
     * Esto demuestra el polimorfismo, ya que el mismo método
     * puede comportarse de manera diferente dependiendo del objeto
     * que lo utiliza.
     */
}