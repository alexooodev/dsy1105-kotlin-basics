package com.example.kotlinbasics

open class Person(val name: String, val age: Int) {
    open fun introduce() {
        println("Hello, my name is $name.")
    }
}

class Employee(
    name: String,
    age: Int,
    val position: String,
    private val salary: Double
) : Person(name, age) {

    fun showPosition() {
        println("$name works as $position.")
    }

    override fun introduce() {
        println("Hello, I'm $name and my position is $position.")
    }
}

fun main() {
    val person = Person("Ana", 30)
    val employee = Employee("Carlos", 28, "Developer", 950000.0)

    person.introduce()
    employee.introduce()
    employee.showPosition()
}