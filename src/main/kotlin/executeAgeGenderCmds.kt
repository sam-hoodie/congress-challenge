fun main() {
    println(getBirthdayComparison("1996-08-08", "1997-03-23", false))
}
fun interpretAgeAndGenderCommand(command: String, data: List<Person>) {
    // -get congress.age.youngest
    // -get congress.age.oldest
    // -get congress.gender.prevalent
    val commandParameters = command.split('.')
    when (commandParameters[1]) {
        "age" -> when (commandParameters[2]) {
            "youngest" -> printMostExtremeAge(true, data)
            "oldest" -> printMostExtremeAge(false, data)
            else -> println("     Invalid Command >> ." + commandParameters[2] + " <<")
        }
        "gender" -> printCommonGender(data)
        else -> println("   Invalid Command >> ." + commandParameters[1] + " <<")
    }
}

fun printCommonGender(data: List<Person>) {
    val allGender = arrayListOf<String>()
    for (element in data) {
        allGender.add(element.bio.gender)
    }
    var maleCount = 0
    var femaleCount = 0
    for (i in 0 until allGender.size) {
        if (allGender[i] == "M") {
            maleCount++
        } else if (allGender[i] == "F") {
            femaleCount++
        }
    }
    if (maleCount > femaleCount) {
        println("  There are more male than female congressmen")
    } else if (femaleCount > maleCount) {
        println("  There are more female than male congressmen")
    } else {
        println("  There is an equal amount of male and female congressmen")
    }
}

fun printMostExtremeAge(youngest: Boolean, data: List<Person>) {
    var extremeBday = data[0].bio.birthday
    for (i in 1 until data.size) {
        if (getBirthdayComparison(data[i].bio.birthday, extremeBday, youngest)) {
            extremeBday = data[i].bio.birthday
        }
    }
    val birthdayParts = extremeBday.split('-')
    if (youngest) {
        println("  The youngest congressman was born on ${monthInterpreter(birthdayParts[1])} ${birthdayParts[2]}, ${birthdayParts[0]}")
    } else {
        println("  The oldest congressman was born on ${monthInterpreter(birthdayParts[1])} ${birthdayParts[2]}, ${birthdayParts[0]}")
    }
}

fun getBirthdayComparison(birthday1: String, birthday2: String, younger: Boolean): Boolean {
    val bday1 = birthday1.split('-')
    val bday2 = birthday2.split('-')
    if (bday1[0].toInt() < bday2[0].toInt()) {
        return !younger
    } else if (bday1[0].toInt() == bday2[0].toInt()) {
        if (bday1[1].toInt() < bday2[1].toInt()) {
            return !younger
        } else if (bday1[1].toInt() == bday2[1].toInt()) {
            if (bday1[2].toInt() < bday2[2].toInt()) {
                return !younger
            } else if (bday1[2].toInt() == bday2[2].toInt()) {
                return false
            }
        }
    }
    return younger
}

fun monthInterpreter(month: String): String {
    return when (month.toInt()) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Invalid Month Number"
    }
}