import java.io.Console

fun interpretNameCommand(command: String, data: List<Person>) {
    // -get congress.names.shortest.first
    // -get congress.names.shortest.last
    // -get congress.names.longest.first
    // -get congress.names.longest.last
    val commandParameters = command.split('.')
    if (commandParameters[3] == "shortest") {
        when (commandParameters[4]) {
            "first" -> println("   The shortest first name is " + getShortestFirst(data))
            "last" -> println("   The shortest last name is " + getShortestLast(data))
            else -> println("   Invalid Command >> ." + commandParameters[4] + " <<")
        }
    } else if (commandParameters[3] == "longest") {
        when (commandParameters[4]) {
            "first" -> println("   The longest first name is " + getLongestFirst(data))
            "last" -> println("   The longest last name is " + getLongestLast(data))
            else -> println("  Invalid Command >> ." + commandParameters[4] + " <<")
        }
    } else {
        println("   Invalid Command >> ." + commandParameters[3] + " <<")
    }
}

fun getShortestFirst(data: List<Person>): String {
    var shortestFirstName = data[1].name.first
    for (i in 1 until data.size) {
        val currentName = data[i].name.first
        if (currentName.length < shortestFirstName.length) {
            shortestFirstName = currentName
        }
    }
    return shortestFirstName
}

fun getLongestFirst(data: List<Person>): String {
    var longestFirstName = data[1].name.first
    for (i in 1 until data.size) {
        val currentName = data[i].name.first
        if (currentName.length > longestFirstName.length) {
            longestFirstName = currentName
        }
    }
    return longestFirstName
}

fun getShortestLast(data: List<Person>): String {
    var shortestLastName = data[1].name.last
    for (i in 1 until data.size) {
        val currentName = data[i].name.last
        if (currentName.length < shortestLastName.length) {
            shortestLastName = currentName
        }
    }
    return shortestLastName
}

fun getLongestLast(data: List<Person>): String {
    var longestLastName = data[1].name.last
    for (i in 1 until data.size) {
        val currentName = data[i].name.last
        if (currentName.length > longestLastName.length) {
            longestLastName = currentName
        }
    }
    return longestLastName
}