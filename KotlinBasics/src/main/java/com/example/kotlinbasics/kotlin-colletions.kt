package com.example.kotlinbasics

fun findProduct(name: String, inventory: List<Map<String, Any>>): Map<String, Any>? {
    for (product in inventory) {
        if (product["name"] == name) {
            return product
        }
    }
    return null
}

fun calculateAveragePrice(inventory: List<Map<String, Any>>): Double {
    var totalPrice = 0.0
    for (product in inventory) {
        val price = (product["price"] as? Double) ?: 0.0
        totalPrice += price
    }
    return totalPrice / inventory.size
}

fun main() {
    val product1 = mapOf("name" to "Gaming Laptop", "price" to 1250.50, "category" to "Technology")
    val product2 = mapOf("name" to "Kotlin Book", "price" to 45.99, "category" to "Books")
    val product3 = mapOf("name" to "Wireless Mouse", "price" to 25.90, "category" to "Technology")
    val product4 = mapOf("name" to "Notebook", "price" to 3.50, "category" to "Books")
    val product5 = mapOf("name" to "Mechanical Keyboard", "price" to 89.99, "category" to "Technology")
    val product6 = mapOf("name" to "Fiction Novel", "price" to 15.75, "category" to "Books")

    val inventory = listOf(product1, product2, product3, product4, product5, product6)

    val searchResult = findProduct("Kotlin Book", inventory)
    println(searchResult)

    val average = calculateAveragePrice(inventory)
    println("Average price: $average")

    val technologyProducts = inventory.filter { it["category"] == "Technology" }
    println(technologyProducts)

    val productNames = inventory.map { it["name"] }
    println(productNames)
}