fun printDirections() {
    println("To get the directions to commands for these genres, type: ")
    println("  0: Names Information")
    println("  1: Age and Gender Information")
    println("  2: Term Information")
    println("  3: Current Legislator Information")
    print("    > ")
    when (readLine().toString().toInt()) {
        0 -> printDirectionsNames()
        1 -> printAgeAndGenderDirections()
        2 -> printTermInfoDirections()
        3 -> printDirectionsCurrent()
    }
    println("        If you want to exit the directions menu, enter 0. If you want to restart, enter 1")
    print("        > ")
    when (readLine().toString().toInt()) {
        0 -> return
        1 -> printDirections()
        else -> println("Invalid Input")
    }
}

fun printDirectionsCurrent() {
    println("      To get the commands for information on current congressmen in specified states, type: ")
    println("      0: Get serving congressmen from a specified state")
    println("      1: Get serving senate members from a specified state")
    println("      2: Get serving house members from a specified state")
    print("        > ")
    when (readLine().toString().toInt()) {
        0 -> println("        Type the command: -get congress.(serving/historic/all).current.(any state name not case sensitive)")
        1 -> println("        Type the command: -get senate.(serving/historic/all).current.(any state name not case sensitive)")
        2 -> println("        Type the command: -get house.(serving/historic/all).current.(any state name not case sensitive)")
        else -> println("          Invalid input")
    }
}

fun printDirectionsNames() {
    println("      To get the commands for information on congressmen names, type: ")
    println("      0: Get shortest first name")
    println("      1: Get shortest last name")
    println("      2: Get longest first name")
    println("      3: Get longest last name")
    print("        > ")
    when (readLine().toString().toInt()) {
        0 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).names.shortest.first")
        1 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).names.longest.first")
        2 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).names.shortest.last")
        3 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).names.longest.last")
        else -> println("          Invalid input")
    }
}

fun printAgeAndGenderDirections() {
    println("      To get the age/gender commands, type: ")
    println("      0: Get the youngest congressman")
    println("      1: Get the oldest congressman")
    println("      2: get the most common gender of the congressmen")
    print("        > ")
    when (readLine().toString().toInt()) {
        0 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).age.youngest")
        1 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).age.oldest")
        2 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).gender.prevalent")
        else -> println("          Invalid input")
    }
}

fun printTermInfoDirections() {
    println("      To get the term information commands, type: ")
    println("      0: Get the most prevalent party")
    println("      1: Get the most prevalent state")
    println("      2: Get the commands for term dates")
    print("        > ")
    when (readLine().toString().toInt()) {
        0 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).terms.pop.party")
        1 -> println("        Type the command: -get (congress/senate/house).(serving/historic/all).terms.pop.state")
        2 -> printTermDateDirections()
        else -> println("          Invalid input")

    }
}

fun printTermDateDirections() {
    println("        To get the term date information commands, type: ")
    println("        0: Get the longest single term")
    println("        1: Get the most time served by one person")
    println("        2: Get the most individual terms served by a person")
    print("          > ")
    when (readLine().toString().toInt()) {
        0 -> println("          Type the command: -get (congress/senate/house).(serving/historic/all).terms.longest_single")
        1 -> println("          Type the command: -get (congress/senate/house).(serving/historic/all).terms.longest_time")
        2 -> println("          Type the command: -get (congress/senate/house).(serving/historic/all).terms.most_terms")
        else -> println("          Invalid input")
    }
}