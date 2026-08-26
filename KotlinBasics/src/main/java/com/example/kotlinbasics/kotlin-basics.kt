fun main() {
    val intNumber: Int = 15
    val doubleNumber: Double = 4.5

    val sum = intNumber + doubleNumber
    val subtraction = intNumber - doubleNumber
    val multiplication = intNumber * doubleNumber
    val division = intNumber / doubleNumber

    println("The sum is: $sum")
    println("The subtraction is: $subtraction")
    println("The multiplication is: $multiplication")
    println("The division is: $division")

    var nullableText: String? = "Hello world"
    println(nullableText?.length)

    nullableText = null
    println(nullableText?.length)

    val weekday: Int = 3

    when (weekday) {
        1 -> println("Monday")
        2 -> println("Tuesday")
        3 -> println("Wednesday")
        4 -> println("Thursday")
        5 -> println("Friday")
        6 -> println("Saturday")
        7 -> println("Sunday")
        else -> println("Invalid number, must be between 1 and 7")
    }
}