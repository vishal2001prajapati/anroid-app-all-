package com.example.myapplication

//Extension Function Simple Demo One
class Circle(val radius: Double) {

    fun area(): Double {
        return Math.PI * radius * radius
    }
}

fun Circle.perimeter(): Double {
    return 2 * Math.PI * radius
}

//Extension Function Simple Demo Two
fun String.findChar(index: Int): Char = this[index]

//Extension Function Simple Demo Three

class SomeExtenstion {
    companion object {

    }
}

fun SomeExtenstion.Companion.displayMessage() {
    println("Extension Function Call")
}


fun main() {
    val circle = Circle(15.0)
    println("Perimeter is: ${circle.perimeter()}")
    println("Area is: ${circle.area()}")
    println("vishal".findChar(0))
    SomeExtenstion.displayMessage()

    //map function

    val map = mapOf<Int, String>(1 to "Vishal", 2 to "Raj", 3 to "Vinayak", 4 to "Jay", 5 to "Pradip", 2 to "Darshan")
    println(map)
    println("Key is:${map.keys}")
    println("Value is:${map.values}")
    println("Rank of 1: ${map[1]}")
    println("Rank 4: ${map.getValue(4)}")
    println("Entries of map :${map.entries}")

    val mapTwo = mutableMapOf<String, Int>("Vishal" to 1, "Raj" to 2, "Vinayak" to 3, "Jay" to 4, "Pradip" to 5)
    println(mapTwo)
    println("MapTwo Key is:${mapTwo.keys}")
    println("MapTwo Value is:${mapTwo.values}")
    //get size of map
    println("MapTwo Size of value:${mapTwo.size}")
    println("MapTwo Size of value:${mapTwo.count()}")
    println("Value of Vishal: ${mapTwo["Vishal"]}")

    //remove entries
    println("Remove Entry:${mapTwo.remove("Vinayak")}")
    println("Entry:${mapTwo.entries}")

    // add entry
    mapTwo["Vaibhav"] = 6
    println("Add: ${mapTwo}")


    //forEach
    mapTwo.forEach {
        println("mapTwo forEach:${it}")
    }

    //value is contain key and value or not for check
    if (mapTwo.containsKey("Pradip")) {
        println("Yes, it contains key Pradip")
    } else {
        println("No, it does not contain Pradip")
    }

    if (mapTwo.containsValue(7)) {
        println("Yes, it contains value 7")
    } else {
        println("No, it does not contain value 7")
    }

    // flatmap
    val data = listOf(Data(listOf("vishal", "jay", "chirag")), Data(listOf("vinay", "openxcell", "google")))
    val merged = data.flatMap { it.items }
    println(merged)

    val mergedMap = data.map { it.items }
    println(mergedMap)

}

class Data(val items: List<String>) {}
