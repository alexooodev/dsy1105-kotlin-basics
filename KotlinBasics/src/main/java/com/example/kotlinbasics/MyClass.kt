package com.example.kotlinbasics

fun main() {

    // ===== Variables y Operadores Aritméticos =====
    val numeroEntero: Int = 10
    val numeroDecimal: Double = 3.5

    val suma = numeroEntero + numeroDecimal
    val resta = numeroEntero - numeroDecimal
    val multiplicacion = numeroEntero * numeroDecimal
    val division = numeroEntero / numeroDecimal

    println("La suma es: $suma")
    println("La resta es: $resta")
    println("La multiplicación es: $multiplicacion")
    println("La división es: $division")


    // ===== Seguridad ante Nulos (Null Safety) =====
    var texto: String? = "Hola Kotlin"
    println("Largo del texto: ${texto?.length}")

    texto = null
    println("Largo del texto (ahora null): ${texto?.length}")

    // En Java, la verificación equivalente sería explícita con un if:
    // if (texto != null) {
    //     System.out.println("Largo del texto: " + texto.length());
    // } else {
    //     System.out.println("Largo del texto: null");
    // }
    // Kotlin evita escribir ese if manualmente: el operador ?. ya hace
    // la comprobación de null internamente y devuelve null en vez de
    // lanzar NullPointerException si el valor es nulo.


    // ===== Lógica Condicional con when =====
    val diaNumero: Int = 3

    when (diaNumero) {
        1 -> println("Lunes")
        2 -> println("Martes")
        3 -> println("Miércoles")
        4 -> println("Jueves")
        5 -> println("Viernes")
        6 -> println("Sábado")
        7 -> println("Domingo")
        else -> println("Número inválido, debe ser entre 1 y 7")
    }
}


//actividad 2
//
//fun main() {
//
//    // ===== Ejercicio 1 =====
//    val calificaciones = listOf(7, 4, 8, 10, 2, 5, 3)
//
//    val aprobadas = calificaciones.filter { it >= 4 }
//    val promedio = aprobadas.average()
//
//    println("Notas aprobadas: $aprobadas")
//    println("Promedio de notas aprobadas: $promedio")
//
//
//    // ===== Ejercicio 2 =====
//    val empleados = listOf("Juan", "Pedro", "Diego")
//    val inventario = mapOf("Manzanas" to 100, "Naranjas" to 80)
//
//    println("\nLista de empleados:")
//    for ((indice, nombre) in empleados.withIndex()) {
//        println("${indice + 1}. $nombre")
//    }
//
//    println("\nReporte de inventario:")
//    for ((producto, stock) in inventario) {
//        println("$producto: $stock unidades")
//    }
//}