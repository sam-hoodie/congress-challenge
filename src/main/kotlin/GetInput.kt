// This is the section of the project that will get executed by the user
fun main() {
    val currentData = parseCongressFile(true)
    val historicData = parseCongressFile(false)
    val fullData = currentData + historicData
    // current commands :)
    // age commands  :(
    // name commands :)
    // term commands :)
    println("To get the different commands for retrieving data, type \"directions\" (not case sensitive)")
    println("To end the process at any time, type \"end\" (not case sensitive)")
    while (true) {
        print("> ")
        val input = readLine().toString()
        if (input.lowercase() == "end") {
            println("Successfully ended")
            break
        }
        val inputParts = input.split('.')
        if (input == "directions") { printDirections(); continue }
        var data = fullData
        if (inputParts[1] == "historic") {
            data = historicData
        } else if (inputParts[1] == "serving") {
            data = currentData
        }
        if (inputParts[0] != "-get congress") {
            val newData: List<Person> = when (inputParts[0]) {
                "-get senate" -> filterSenateCongress("senate", data)
                "-get house" -> filterSenateCongress("house", data)
                else -> data
            }
            interpret(input, newData)
        } else {
            interpret(input, data)
        }
    }
}

fun interpret(input: String, data: List<Person>) {
    // -get house.current.georgia
    val inputParts = input.split(".")
    if (input.lowercase() == "end") {
        println("Successfully Ended")
        return
    }
    if (input.lowercase() == "directions"){
        printDirections()
        return
    }
    when (inputParts[2].lowercase()) {
        "names" -> interpretNameCommand(input, data)
        "gender" -> interpretAgeAndGenderCommand(input, data)
        "age" -> interpretAgeAndGenderCommand(input, data)
        "terms" -> interpretTermCmds(input, data)
        "current" -> interpretCurrentCommands(input, data)
    }
}

fun filterSenateCongress(type: String, data: List<Person>): List<Person> {
    val result = ArrayList<Person>()
    for (i in data.indices) {
        for (i2 in 0 until data[i].terms.size) {
            when (type) {
                "house" -> if (data[i].terms[i2].type == "rep") { result.add(data[i]) }
                "senate" -> if (data[i].terms[i2].type == "sen") { result.add(data[i]) }
            }
        }
    }
    return result
}