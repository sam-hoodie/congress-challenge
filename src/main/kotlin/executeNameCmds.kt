fun interpretNameCommand(command: String, data: List<Person>) {
    // -get congress.names.shortest.first
    // -get congress.names.shortest.last
    // -get congress.names.longest.first
    // -get congress.names.longest.last

    val commandParameters = command.split('.')
    if (commandParameters[2] == "shortest") {
        if (commandParameters[3] == "first") {
            println("   The shortest first name is " + getShortestFirst(data))
        } else if (commandParameters[3] == "last") {
            println("   The shortest last name is " + getShortestLast(data))
        } else {
            println("   Invalid Command >> ." + commandParameters[3] + " <<")
        }
    } else if (commandParameters[2] == "longest") {
        if (commandParameters[3] == "first") {
            println("   The longest first name is " + getLongestFirst(data))
        } else if (commandParameters[3] == "last") {
            println("   The longest last name is " + getLongestLast(data))
        } else {
            println("  Invalid Command >> ." + commandParameters[3] + " <<")
        }
    } else {
        println("   Invalid Command >> ." + commandParameters[2] + " <<")
    }
}

fun getShortestFirst(data: List<Person>): String {
    var shortestFirstName = data[1].name.first
    for (i in 1..data.size - 1) {
        val currentName = data[i].name.first
        if (currentName.length < shortestFirstName.length) {
            shortestFirstName = currentName
        }
    }
    return shortestFirstName
}

fun getLongestFirst(data: List<Person>): String {
    var longestFirstName = data[1].name.first
    for (i in 1..data.size - 1) {
        val currentName = data[i].name.first
        if (currentName.length > longestFirstName.length) {
            longestFirstName = currentName
        }
    }
    return longestFirstName
}

fun getShortestLast(data: List<Person>): String {
    var shortestLastName = data[1].name.last
    for (i in 1..data.size - 1) {
        val currentName = data[i].name.last
        if (currentName.length < shortestLastName.length) {
            shortestLastName = currentName
        }
    }
    return shortestLastName
}

fun getLongestLast(data: List<Person>): String {
    var longestLastName = data[1].name.last
    for (i in 1..data.size - 1) {
        val currentName = data[i].name.last
        if (currentName.length > longestLastName.length) {
            longestLastName = currentName
        }
    }
    return longestLastName
}