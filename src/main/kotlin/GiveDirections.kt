fun main() {

}

fun printDirections() {
    println("To get the directions to commands for these genres, type: ")
    println("  0: Names Information")
    print("    > ")

    val directionInput = readLine().toString().toInt()
    if (directionInput == 0) {
        printDirectionsNames()
    }
 }

fun printDirectionsNames() {
    println("      To get the commands for information on congressmen names, type: ")
    println("      0: Get shortest first name")
    println("      1: Get shortest last name")
    println("      2: Get longest first name")
    println("      3: Get longest last name")
    print("        > ")
    printDirectionsNames()
    val subinput = readLine().toString().toInt()
    if (subinput == 0) { println("        Type the command: get congress.names.shortestFirst") }
    if (subinput == 1) { println("        Type the command: get congress.names.longestFirst") }
    if (subinput == 2) { println("        Type the command: get congress.names.shortestLast") }
    if (subinput == 3) { println("        Type the command: get congress.names.longestLast") }
}