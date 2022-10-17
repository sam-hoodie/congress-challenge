fun main() {
    val data = parseCongressFile(true)
    interpretAgeAndGenderCommand("-get house.serving.age.oldest", data)
}

fun interpretAgeAndGenderCommand(command: String, data: List<Person>) {
    // -get congress.age.youngest
    // -get congress.age.oldest
    // -get congress.gender.prevalent
    if (command.startsWith("-get congress.all.age") || command.startsWith("-get congress.historic.age")) {
        println("  The age commands are incompatible with historic data files")
        return
    }
    val commandParameters = command.split('.')
    if (commandParameters[2] == "age") {
        if (commandParameters[3] == "youngest") {
            println("  The youngest congressman was born on ${getMostExtremeAge(true, data)}")
            return
        }
        if (commandParameters[3] == "oldest") {
            println("  The oldest congressman was born on ${getMostExtremeAge(false, data)}")
            return
        }
    }
    if (commandParameters[2] == "gender") {
        when (getCommonGender(data)) {
            CommonGender.MALE -> println("  There are more male than female congressmen")
            CommonGender.FEMALE -> println("  There are more female than male congressmen")
            CommonGender.SAME -> println("  There are the same amount of male and female congressmen")
        }
        return
    }
    printAutocorrect(command)
}

enum class CommonGender {
    MALE,
    FEMALE,
    SAME
}

fun getCommonGender(data: List<Person>): CommonGender {
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
    return if (maleCount > femaleCount) {
        CommonGender.MALE
    } else if (femaleCount > maleCount) {
        CommonGender.FEMALE
    } else {
        CommonGender.SAME
    }
}

fun getMostExtremeAge(youngest: Boolean, data: List<Person>): String {
    var extremeBday = "0000-00-00"
    if (!youngest) {
        extremeBday = "9999-99-99"
    }
    for (i in data.indices) {
        if (data[i].bio.birthday != null) {
            if (getBirthdayComparison(data[i].bio.birthday.toString(), extremeBday, youngest)) {
                extremeBday = data[i].bio.birthday.toString()
            }
        }
    }
    val birthdayParts = extremeBday.split('-')
    return monthInterpreter(birthdayParts[1].toInt().toString()) + " " + birthdayParts[2] + ", " + birthdayParts[0]
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