import org.apache.commons.text.similarity.LevenshteinDistance
fun printAutocorrect(command: String) {
    print("  Invalid command!")
    val printed = printInvalidType(command)
    if (printed) {
        return
    }
    val corrected = autocorrect(command)
    if (corrected.isEmpty()) {
        println(" No autocorrect suggestions!")
        return
    }
    print(" Try replacing it with one of these:")
    println()
    for (i in corrected.indices) {
        println("  -get ${corrected[i]}")
    }
}

fun printInvalidType(command: String): Boolean {
    val type = getCmdExceptionType(command)
    if (type == CommandExceptionType.NONE) {
        return false
    }
    when (type) {
        CommandExceptionType.NO_SPACE -> println(" -> Invalid get formatting!")
        CommandExceptionType.NO_GET -> println(" -> Missing -get command!")
        CommandExceptionType.NO_DOTS -> println(" -> Invalid command formatting!")
        CommandExceptionType.NOT_ENOUGH_PARTS -> println(" -> Insufficient amount of arguments")
        CommandExceptionType.STARTS_WITH_SPACE -> println(" -> Invalid starting space(s)")
        else -> {}
    }
    return true
}

enum class CommandExceptionType {
    NO_SPACE,
    NO_GET,
    NO_DOTS,
    NOT_ENOUGH_PARTS,
    STARTS_WITH_SPACE,
    NONE
}

fun getCmdExceptionType(command: String): CommandExceptionType? {
    if (!command.startsWith("-get ")) {
        return CommandExceptionType.NO_GET
    }
    if (!command.contains(' ')) {
        return CommandExceptionType.NO_SPACE
    }
    if (!command.contains('.')) {
        return CommandExceptionType.NO_DOTS
    }
    if (command.split('.').size < 4) {
        return CommandExceptionType.NOT_ENOUGH_PARTS
    }
    if (command.startsWith(" ")) {
        return CommandExceptionType.STARTS_WITH_SPACE
    }
    return CommandExceptionType.NONE
}

fun autocorrect(input: String): List<String> {
    var parts = arrayListOf(input.split(' ')[1].split('.'))
    val currentOptions = provideAutocorrectOptions(parts[0][2], 2, false)
    var containsCurrent = false
    if (currentOptions.contains("current") || parts[0][2] == "current") {
        containsCurrent = true
    }
    for (i in parts.indices) {
        for (i2 in parts[i].indices) {
            val currentPartsSize = parts.size
            val currentAutoCorrectSuggestions = provideAutocorrectOptions(parts[i][i2], i2, containsCurrent)
            for (i3 in currentAutoCorrectSuggestions.indices) {
                val currentNewCommand =
                    parts[i].subList(0, i2) + currentAutoCorrectSuggestions[i3] + parts[i].subList(
                        i2 + 1,
                        parts[i].size
                    )
                if (currentAutoCorrectSuggestions[i3] != "same" && currentAutoCorrectSuggestions[i3] != "none") {
                    parts.add(currentNewCommand)
                } else {
                    parts.add(parts[i])
                }
            }
            parts = removeParts(parts, currentPartsSize)
        }
    }
    val result = arrayListOf<String>()
    for (i in parts.indices) {
        if (isValidCommand(connectWithDot(parts[i]))) {
            result.add(connectWithDot(parts[i]))
        }
    }
    return result
}


fun removeParts(parts: ArrayList<List<String>>, toRemove: Int): ArrayList<List<String>> {
    for (i in 1..toRemove) {
        parts.removeAt(0)
    }
    return parts
}

fun isValidCommand(command: String): Boolean {
    val parts = command.split('.')
    for (i in parts.indices) {
        if (!isValidPart(parts[i], i)) {
            return false
        }
    }
    return true
}

fun isValidPart(word: String, index: Int): Boolean {
    val valid = when (index) {
        0 -> listOf("congress", "senate", "house")
        1 -> listOf("all", "serving", "historic")
        2 -> listOf("current", "names", "age", "gender", "terms")
        3 -> listOf(
            "shortest", "longest", "youngest", "oldest", "prevalent",
            "longest_single", "longest_time", "most_terms", "pop"
        )
        4 -> listOf("first", "last", "party", "state")
        else -> listOf()
    }.toMutableList()
    if (index == 3) {
        valid += getAllSateNames()
    }
    if (valid.contains(word)) {
        return true
    }
    return false
}

fun provideAutocorrectOptions(word: String, part: Int, current: Boolean): List<String> {
    var wordsToCheckFor = listOf("")
    val suggestions = arrayListOf<String>()
    when (part) {
        0 -> wordsToCheckFor = listOf("congress", "senate", "house")
        1 -> wordsToCheckFor = listOf("all", "serving", "historic")
        2 -> wordsToCheckFor = listOf("current", "names", "age", "gender", "terms")
        3 -> wordsToCheckFor = listOf(
            "shortest", "longest", "youngest", "oldest", "prevalent",
            "longest_single", "longest_time", "most_terms", "pop"
        )
        4 -> wordsToCheckFor = listOf("first", "last", "party", "state")
    }
    if (current && part == 3) {
        wordsToCheckFor = getAllSateNames()
    }
    if (wordsToCheckFor.contains(word)) {
        return listOf("same")
    }
    suggestions.addAll(autocorrectSuggestions(word, wordsToCheckFor))
    return suggestions
}

fun connectWithDot(words: List<String>): String {
    var result = ""
    for ((index, word) in words.withIndex()) {
        result += if (index == 0) {
            word
        } else {
            (".$word")
        }
    }
    return result
}

fun autocorrectSuggestions(word: String, wordsToCheckFor: List<String>): List<String> {
    val possibleWords = arrayListOf<String>()
    for (i in wordsToCheckFor.indices) {
        when {
            word.length == 2 -> if (levenshteinFast(word, wordsToCheckFor[i]) <= 1) {
                possibleWords.add(wordsToCheckFor[i])
            }
            (word.length == 3 || word.length == 4) -> if (levenshteinFast(word, wordsToCheckFor[i]) <= 2) {
                possibleWords.add(wordsToCheckFor[i])
            }
            word.length >= 8 -> if (levenshteinFast(word, wordsToCheckFor[i]) <= 4) {
                possibleWords.add(wordsToCheckFor[i])
            }
            else -> if (levenshteinFast(word, wordsToCheckFor[i]) <= 3) {
                possibleWords.add(wordsToCheckFor[i])
            }
        }
    }
    if (possibleWords.isEmpty()) {
        return listOf("none")
    }
    return possibleWords
}

//fun levenshteinRecursive(a: String, b: String): Int {
//    if (b.isEmpty()) {
//        return a.length
//    }
//    if (a.isEmpty()) {
//        return b.length
//    }
//    if (a[0] == b[0]) {
//        return levenshteinRecursive(a.substring(1), b.substring(1))
//    }
//    return 1 + min(
//        min(
//            levenshteinRecursive(a.substring(1), b),
//            levenshteinRecursive(a, b.substring(1))
//        ),
//        levenshteinRecursive(a.substring(1), b.substring(1))
//    )
//}

fun levenshteinFast(s: String, t: String): Int {
    // Use Apache Commons Text library
    return LevenshteinDistance().apply(s, t)
}

fun getAllSateNames(): List<String> {
    return listOf(
        "alabama",
        "alaska",
        "arizona",
        "arkansas",
        "california",
        "colorado",
        "connecticut",
        "delaware",
        "florida",
        "georgia",
        "hawaii",
        "idaho",
        "illinois",
        "indiana",
        "iowa",
        "kansas",
        "kentucky",
        "louisiana",
        "maine",
        "maryland",
        "massachusetts",
        "michigan",
        "minnesota",
        "mississippi",
        "missouri",
        "montana",
        "nebraska",
        "nevada",
        "new_hampshire",
        "new_jersey",
        "new_mexico",
        "new_york",
        "north_carolina",
        "north_dakota",
        "ohio",
        "oklahoma",
        "oregon",
        "pennsylvania",
        "Rhode_island",
        "south_carolina",
        "south_dakota",
        "tennessee",
        "texas",
        "utah",
        "vermont",
        "virginia",
        "washington",
        "west_virginia",
        "wisconsin"
    )
}