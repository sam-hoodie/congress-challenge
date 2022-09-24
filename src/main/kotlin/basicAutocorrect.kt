import kotlin.math.min

fun main() {
    // current
    // all
    // historic
    //      |   0       1       2      3
    // -get congress.serving.current.georgia
    println(provideAutocorrectOptions("-get senate.serving.terms.pop.state", 4))
}

fun provideAutocorrectOptions(command: String, partToFix: Int): List<String> {
    val parts = command.split(' ')[1].split('.')
    var wordsToCheckFor = listOf("")
    var suggestions = arrayListOf<String>()
    val wordToFix = parts[partToFix]
    when (partToFix) {
        0 -> wordsToCheckFor = listOf("congress", "senate", "house")
        1 -> wordsToCheckFor = listOf("all", "serving", "historic")
        2 -> wordsToCheckFor = listOf("current", "names", "age", "gender", "terms")
        3 -> wordsToCheckFor = listOf(
            "shortest", "longest", "youngest", "oldest", "prevalent",
            "longest_single", "longest_time", "most_terms", "pop"
        )
        4 -> wordsToCheckFor = listOf("first", "last", "party", "state")
    }
    if (partToFix == 3 && parts[2] == "current") {
        suggestions.addAll(autocorrectSuggestions(parts[3], getAllSateNames()) as ArrayList<String>)
    } else {
        suggestions.addAll(autocorrectSuggestions(parts[partToFix], wordsToCheckFor))
    }
    val newCommandParts = arrayListOf<String>()
    val suggestedCommands = arrayListOf<String>()
    val newParts = command.split('.')
    if (suggestions[0] != "none") {
        for (word in suggestions.indices) {
            for (cmdPart in newParts.indices) {
                if (cmdPart == 0) {
                    if (cmdPart != partToFix) {
                        newCommandParts.add(newParts[0])
                    } else {
                        newCommandParts.add("-get " + suggestions[word])
                    }
                } else {
                    if (cmdPart != partToFix) {
                        newCommandParts.add(newParts[cmdPart])
                    } else {
                        newCommandParts.add(suggestions[word])
                    }
                }
            }
            suggestedCommands.add(connectWithDot(newCommandParts))
            newCommandParts.clear()
        }
    } else {
        return listOf("none")
    }
    if (suggestedCommands.contains(command)) {
        return listOf("same")
    }
    return suggestedCommands
}

fun connectWithDot(words: List<String>): String {
    var result = ""
    for ((index, word) in words.withIndex()) {
        if (index == 0) {
            result += word
        } else {
            result += (".$word")
        }
    }
    return result
}

fun autocorrectSuggestions(word: String, wordsToCheckFor: List<String>): List<String> {
    val possibleWords = arrayListOf<String>()
    for (i in wordsToCheckFor.indices) {
        when  {
            word.length == 2 -> if (levenshteinRecursive(word, wordsToCheckFor[i]) <= 2) {
                possibleWords.add(wordsToCheckFor[i])
            }
            (word.length == 3 || word.length == 4) -> if (levenshteinRecursive(word, wordsToCheckFor[i]) <= 3) {
                possibleWords.add(wordsToCheckFor[i])
            }
            word.length >= 8 -> if (levenshteinRecursive(word, wordsToCheckFor[i]) <= 5) {
                possibleWords.add(wordsToCheckFor[i])
            }
            else -> if (levenshteinRecursive(word, wordsToCheckFor[i]) <= 4) {
                possibleWords.add(wordsToCheckFor[i])
            }
        }
    }
    if (possibleWords.isEmpty()) {
        return listOf("none")
    }
    return possibleWords
}

fun levenshteinRecursive(a: String, b: String): Int {
    if (b.isEmpty()) {
        return a.length
    }
    if (a.isEmpty()) {
        return b.length
    }
    if (a[0] == b[0]) {
        return levenshteinRecursive(a.substring(1), b.substring(1))
    }
    return 1 + min(
        min(
            levenshteinRecursive(a.substring(1), b),
            levenshteinRecursive(a, b.substring(1))
        ),
        levenshteinRecursive(a.substring(1), b.substring(1))
    )
}

fun getAllSateNames(): List<String> {
    return listOf(
        "Alabama",
        "Alaska",
        "Arizona",
        "Arkansas",
        "California",
        "Colorado",
        "Connecticut",
        "Delaware",
        "Florida",
        "Georgia",
        "Hawaii",
        "Idaho",
        "Illinois",
        "Indiana",
        "Iowa",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Maine",
        "Maryland",
        "Massachusetts",
        "Michigan",
        "Minnesota",
        "Mississippi",
        "Missouri",
        "Montana",
        "Nebraska",
        "Nevada",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "New York",
        "North Carolina",
        "North Dakota",
        "Ohio",
        "Oklahoma",
        "Oregon",
        "PennsylvaniaRhode Island",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Vermont",
        "Virginia",
        "Washington",
        "West Virginia",
        "Wisconsin"
    )
}