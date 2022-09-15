// This is the section of the project that will get executed by the user
fun main() {
    val data = parseCongressFile()
    var input = ""
    println("To get the different commands for retrieving data, type \"directions\" (not case sensitive)")
    while (input.lowercase() != "end") {
        print("> ")
        input = readLine().toString()
        interpret(input, data)
    }
}

fun interpret(input: String, data: List<Person>) {
    if (input.lowercase() == "end") {
        println("Successfully Ended")
        return
    }
    if (input.lowercase().startsWith("-get congress.names")) {
        interpretNameCommand(input, data)
    }
    if (input.lowercase().startsWith("-get congress.age") || input.lowercase().startsWith("-get congress.gender")) {
        interpretAgeAndGenderCommand(input, data)
    }
    if (input.lowercase().startsWith("-get congress.terms")) {
        interpretTermCmds(input, data)
    }
    if (input.lowercase() == "directions"){
        printDirections()
    }
}

