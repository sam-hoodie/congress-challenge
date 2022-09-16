// This is the section of the project that will get executed by the user
fun main() {
    val data = parseCongressFile()
    var input = ""
    println("To get the different commands for retrieving data, type \"directions\" (not case sensitive)")
    while (input.lowercase() != "end") {
        print("> ")
        input = readLine().toString()
        val inputParts = input.split('.')
        val newData: List<Person> = when (inputParts[0]) {
            "-get senate" -> filterSenateCongress("senate", data)
            "-get house" -> filterSenateCongress("house", data)
            else -> data
        }
        interpret(input, newData)
    }
}

fun interpret(input: String, data: List<Person>) {
    val inputParts = input.split(".")
    if (input.lowercase() == "end") {
        println("Successfully Ended")
        return
    }
    if (input.lowercase() == "directions"){
        printDirections()
    }
    when (inputParts[1].lowercase()) {
        "names" -> interpretNameCommand(input, data)
        "gender" -> interpretAgeAndGenderCommand(input, data)
        "age" -> interpretAgeAndGenderCommand(input, data)
        "terms" -> interpretTermCmds(input, data)
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