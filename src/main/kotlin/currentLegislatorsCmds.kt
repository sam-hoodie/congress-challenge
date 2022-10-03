import kotlin.collections.HashMap

fun main() {
    // -get congress.current.georgia
    // -get senate.current.georgia
    // -get house.serving.current.georgia
//    println(stateToAbbreviation("Alabama"))
//    val example = listOf(listOf("world", "2"), listOf("hello", "2"), listOf("!!!", "3"))
//    println(sortByDistrict(example))
//    val data = parseCongressFile(true)
//    println(interpretCurrentCommands("-get congress.serving.current.georgai", data))
}

fun interpretCurrentCommands(command: String, data: List<Person>) {
    val cmdParts = command.split(' ')[1].split('.')
    val stateToFind = stateToAbbreviation(cmdParts[3].lowercase())
    var legislators = getLegislators(data, stateToFind, command)
    if (legislators.isNotEmpty()) {
        legislators = legislators.toSet().toList() as ArrayList<List<String>>
        if (cmdParts[0] != "house") {
            for (i in legislators.indices) {
                when (cmdParts[0]) {
                    "senate" -> println("  " + legislators[i][0])
                    "congress" -> println("  " + legislators[i][0] + " (" + legislators[i][1] + ")")
                }
            }
            return
        } else if (cmdParts[0] == "house") {
            val sorted = sortByDistrict(legislators)
            for (i in sorted.indices) {
                val district = sorted[i][1]
                println("  " + sorted[i][0] + " (District " + district + ")")
            }
            return
        }
    }
    printAutocorrect(command)
}

fun getLegislators(data: List<Person>, stateToFind: String, command: String): List<List<String>> {
    val cmdParts = command.split(' ')[1].split('.')
    val legislators = arrayListOf<List<String>>()
    for (i in 0..data.size - 1) {
        val terms = data[i].terms
        if (terms[terms.size - 1].state == stateToFind) {
            if (cmdParts[0] == "congress") {
                if (terms[terms.size - 1].type == "rep") {
                    val partToAdd = listOf((data[i].name.first + " " + data[i].name.last), "House")
                    legislators.add(partToAdd)
                } else {
                    val partToAdd = listOf((data[i].name.first + " " + data[i].name.last), "Senate")
                    legislators.add(partToAdd)
                }
            }
            if (cmdParts[0] == "senate") {
                if (terms[terms.size - 1].type == "sen") {
                    val partToAdd = listOf((data[i].name.first + " " + data[i].name.last))
                    legislators.add(partToAdd)
                }
            }
            if (cmdParts[0] == "house") {
                if (terms[terms.size - 1].type == "rep") {
                    val partToAdd = listOf(
                        (data[i].name.first + " " + data[i].name.last),
                        terms[terms.size - 1].district.toString()
                    )
                    legislators.add(partToAdd)
                }
            }
        }
    }
    return legislators
}

fun sortByDistrict(input: List<List<String>>): List<List<String>> {
    var district = 1
    val result = arrayListOf<List<String>>()
    while (result.size != input.size) {
        for (i in 0..input.size - 1) {
            if (input[i][1].toInt() == district) {
                result.add(input[i])
            }
        }
        district++
    }
    return result
}


fun stateToAbbreviation(state: String): String {
    val states = HashMap<String, String>()
    states["alabama"] = "AL"
    states["alaska"] = "AK"
    states["alberta"] = "AB"
    states["american_samoa"] = "AS"
    states["arizona"] = "AZ"
    states["arkansas"] = "AR"
    states["british_columbia"] = "BC"
    states["california"] = "CA"
    states["colorado"] = "CO"
    states["connecticut"] = "CT"
    states["delaware"] = "DE"
    states["district_of_columbia"] = "DC"
    states["florida"] = "FL"
    states["georgia"] = "GA"
    states["guam"] = "GU"
    states["hawaii"] = "HI"
    states["idaho"] = "ID"
    states["illinois"] = "IL"
    states["indiana"] = "IN"
    states["iowa"] = "IA"
    states["kansas"] = "KS"
    states["kentucky"] = "KY"
    states["louisiana"] = "LA"
    states["maine"] = "ME"
    states["manitoba"] = "MB"
    states["maryland"] = "MD"
    states["massachusetts"] = "MA"
    states["michigan"] = "MI"
    states["minnesota"] = "MN"
    states["mississippi"] = "MS"
    states["missouri"] = "MO"
    states["montana"] = "MT"
    states["nebraska"] = "NE"
    states["nevada"] = "NV"
    states["new_brunswick"] = "NB"
    states["new_hampshire"] = "NH"
    states["new_jersey"] = "NJ"
    states["new_mexico"] = "NM"
    states["new_york"] = "NY"
    states["newfoundland"] = "NF"
    states["north_carolina"] = "NC"
    states["north_dakota"] = "ND"
    states["northwest_territories"] = "NT"
    states["nova_scotia"] = "NS"
    states["nunavut"] = "NU"
    states["ohio"] = "OH"
    states["oklahoma"] = "OK"
    states["ontario"] = "ON"
    states["oregon"] = "OR"
    states["pennsylvania"] = "PA"
    states["puerto_rico"] = "PR"
    states["quebec"] = "QC"
    states["rhode_island"] = "RI"
    states["saskatchewan"] = "SK"
    states["south_carolina"] = "SC"
    states["south_dakota"] = "SD"
    states["tennessee"] = "TN"
    states["texas"] = "TX"
    states["utah"] = "UT"
    states["vermont"] = "VT"
    states["virgin_islands"] = "VI"
    states["virginia"] = "VA"
    states["washington"] = "WA"
    states["west_virginia"] = "WV"
    states["wisconsin"] = "WI"
    states["wyoming"] = "WY"
    states["yukon_territory"] = "YT"
    return states[state].toString()
}