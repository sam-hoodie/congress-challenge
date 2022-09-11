fun main() {

}

fun printDirections() {
    println("To get the directions to commands for these genres, type: ")
    println("  0: Names Information")
    println("  1: Age and Gender Information")
    println("  2: Term Information")
    print("    > ")

    val directionInput = readLine().toString().toInt()
    if (directionInput == 0) {
        printDirectionsNames()
    }
    if (directionInput == 1) {
        printAgeAndGenderDirections()
    }
    if (directionInput == 2) {
        printTermInfoDirections()
    }
}
fun printDirectionsNames() {
    println("      To get the commands for information on congressmen names, type: ")
    println("      0: Get shortest first name")
    println("      1: Get shortest last name")
    println("      2: Get longest first name")
    println("      3: Get longest last name")
    print("        > ")
    val subinput = readLine().toString().toInt()
    if (subinput == 0) { println("        Type the command: -get congress.names.shortestFirst"); return }
    if (subinput == 1) { println("        Type the command: -get congress.names.longestFirst"); return }
    if (subinput == 2) { println("        Type the command: -get congress.names.shortestLast"); return }
    if (subinput == 3) { println("        Type the command: -get congress.names.longestLast "); return }
    println("          Invalid input")
}
fun printAgeAndGenderDirections() {
    println("      To get the age/gender commands, type: ")
    println("      0: Get the youngest congressman")
    println("      1: Get the oldest congressman")
    println("      2: Get the average age of the congressmen")
    println("      3: get the most common gender of the congressmen")
    print("        > ")
    val subinput = readLine().toString().toInt()
    if (subinput == 0) { println("        Type the command: -get congress.age.youngest"); return }
    if (subinput == 1) { println("        Type the command: -get congress.age.oldest"); return }
    if (subinput == 2) { println("        Type the command: -get congress.age.average"); return }
    if (subinput == 3) { println("        Type the command: -get congress.gender.prevalent"); return }
    println("          Invalid input")
}

fun printTermInfoDirections() {
    println("      To get the term information commands, type: ")
    println("      0: Get the most prevalent party")
    println("      1: Get the most prevalent state")
    println("      2: Get the commands for term dates")
    print("        > ")
    val subinput = readLine().toString().toInt()
    if (subinput == 0) { println("        Type the command: -get congress.terms.pop.state"); return }
    if (subinput == 1) { println("        Type the command: -get congress.terms.pop.party"); return }
    if (subinput == 2) { printTermDateDirections(); return }
    println("          Invalid input")
}

fun printTermDateDirections() {
    println("        To get the term date information commands, type: ")
    println("        0: Get the longest single term")
    println("        1: Get the most time served by one person")
    println("        2: Get the most individual terms served by a person")
    print("          > ")
    val subinput = readLine().toString().toInt()
    if (subinput == 0) { println("          Type the command: -get congress.terms.longest_single"); return }
    if (subinput == 1) { println("          Type the command: -get congress.terms.longest_time"); return }
    if (subinput == 2) { println("          Type the command: -get congress.terms.most_terms"); return }
    println("          Invalid input")
}