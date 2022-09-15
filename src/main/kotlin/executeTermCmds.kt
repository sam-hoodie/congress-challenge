import java.time.Duration
import java.time.LocalDateTime

fun main() {
//    val data = parseCongressFile()
//    printMostPopState(data)
//    interpretTermCmds("-get congress.terms.pop.state", data)
}

fun interpretTermCmds(command: String, data: List<Person>) {
    val commandParameters = command.split('.')
    if (commandParameters[2] == "pop") {
        when (commandParameters[3]) {
            "state" -> printMostPopState(data)
            "party" -> printMostPopParty(data)
            else -> println("Invalid command >> ${commandParameters[3]} <<")
        }
    } else if (commandParameters[2] == "most_terms") {
        printMostTerms(data)
    } else if (commandParameters[2] == "longest_time") {
        printMostDaysServed(data)
    }
}

fun printMostDaysServed(data: List<Person>) {
    var mostDaysServed = 0
    var personWithMost = ""
    for (element in data) {
        val daysServed = getTotalDays(element.terms)
        if (daysServed > mostDaysServed) {
            mostDaysServed = daysServed
            personWithMost = data[0].name.first + " " + data[0].name.last
        }
    }
    println("  The longest amount of time served by one congressman is $mostDaysServed days by $personWithMost")
}

fun getTotalDays(terms: List<Term>): Int {
    var result = 0
    for (i in terms.indices) {
        result += getDaysBetween(terms[i].start, terms[i].end)
    }
    return result
}

fun printMostTerms(data: List<Person>) {
    var mostTerms = data[0].terms.size
    var personWithMost = data[0].name.first + " " + data[0].name.last
    for (i in 1 until data.size) {
        if (data[i].terms.size > mostTerms) {
            mostTerms = data[i].terms.size
            personWithMost = data[i].name.first + " " + data[i].name.last
        }
    }
    println("  The most terms served by a person is $personWithMost with $mostTerms separate terms served")
}

fun printMostPopParty(data: List<Person>) {
    val uniqueParties = getAllParties(data).toSet().toList()
    val parties = getAllParties(data)
    var mostPopularParty = ""
    var amountOfMostPopParty = 0
    for (i in uniqueParties.indices) {
        val predicate: (String) -> Boolean = {it == parties[i]}
        val stateAppearances = parties.count(predicate)
        if (stateAppearances > amountOfMostPopParty) {
            amountOfMostPopParty = stateAppearances
            mostPopularParty = parties[i]
        }
    }
    println("  The most popular party is $mostPopularParty" +
            " with $amountOfMostPopParty appearances")
}

fun getAllParties(data: List<Person>): List<String> {
    val result = arrayListOf<String>()
    for (element in data) {
        val terms = element.terms
        for (element2 in terms) {
            result.add(element2.party)
        }
    }
    return result
}

fun getDaysBetween(date1: String, date2: String): Int {
    val firstDate = LocalDateTime.parse(date1 + "T20:00:00.0000")
    val secondDate = LocalDateTime.parse(date2 + "T20:00:00.0000")
    return Duration.between(firstDate, secondDate).toDays().toInt()
}

fun printMostPopState(data: List<Person>) {
    val allStates = getAllStates(data)
    val uniqueStates = allStates.toSet().toList()
    var mostPopularState = ""
    var amountOfMostPopState = 0
    for (i in uniqueStates.indices) {
        val predicate: (String) -> Boolean = {it == allStates[i]}
        val stateAppearances = allStates.count(predicate)
        if (stateAppearances > amountOfMostPopState) {
            amountOfMostPopState = stateAppearances
            mostPopularState = uniqueStates[i]
        }
    }
    println("  The most popular state is ${convertStateNames(mostPopularState)}" +
            " with $amountOfMostPopState appearances")
}
fun getAllStates(data: List<Person>): List<String> {
    val result = arrayListOf<String>()
    for (element in data) {
        val terms = element.terms
        for (element2 in terms) {
            result.add(element2.state)
        }
    }
    return result
}

fun convertStateNames(state: String): String{
    val states = HashMap<String, String>()
    states.put("AL", "Alabama")
    states.put("AK", "Alaska")
    states.put("AB", "Alberta")
    states.put("AZ", "Arizona")
    states.put("AR", "Arkansas")
    states.put("BC", "British Columbia")
    states.put("CA", "California")
    states.put("CO", "Colorado")
    states.put("CT", "Connecticut")
    states.put("DE", "Delaware")
    states.put("DC", "District Of Columbia")
    states.put("FL", "Florida")
    states.put("GA", "Georgia")
    states.put("GU", "Guam")
    states.put("HI", "Hawaii")
    states.put("ID", "Idaho")
    states.put("IL", "Illinois")
    states.put("IN", "Indiana")
    states.put("IA", "Iowa")
    states.put("KS", "Kansas")
    states.put("KY", "Kentucky")
    states.put("LA", "Louisiana")
    states.put("ME", "Maine")
    states.put("MB", "Manitoba")
    states.put("MD", "Maryland")
    states.put("MA", "Massachusetts")
    states.put("MI", "Michigan")
    states.put("MN", "Minnesota")
    states.put("MS", "Mississippi")
    states.put("MO", "Missouri")
    states.put("MT", "Montana")
    states.put("NE", "Nebraska")
    states.put("NV", "Nevada")
    states.put("NB", "New Brunswick")
    states.put("NH", "New Hampshire")
    states.put("NJ", "New Jersey")
    states.put("NM", "New Mexico")
    states.put("NY", "New York")
    states.put("NF", "Newfoundland")
    states.put("NC", "North Carolina")
    states.put("ND", "North Dakota")
    states.put("NT", "Northwest Territories")
    states.put("NS", "Nova Scotia")
    states.put("NU", "Nunavut")
    states.put("OH", "Ohio")
    states.put("OK", "Oklahoma")
    states.put("ON", "Ontario")
    states.put("OR", "Oregon")
    states.put("PA", "Pennsylvania")
    states.put("PE", "Prince Edward Island")
    states.put("PR", "Puerto Rico")
    states.put("QC", "Quebec")
    states.put("RI", "Rhode Island")
    states.put("SK", "Saskatchewan")
    states.put("SC", "South Carolina")
    states.put("SD", "South Dakota")
    states.put("TN", "Tennessee")
    states.put("TX", "Texas")
    states.put("UT", "Utah")
    states.put("VT", "Vermont")
    states.put("VI", "Virgin Islands")
    states.put("VA", "Virginia")
    states.put("WA", "Washington")
    states.put("WV", "West Virginia")
    states.put("WI", "Wisconsin")
    states.put("WY", "Wyoming")
    states.put("YT", "Yukon Territory")
    states.put("Yukon Territory","YT")
    return states[state].toString()
}