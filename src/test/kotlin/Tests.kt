import kotlin.test.Test
import kotlin.test.assertEquals

class AutocorrectTests {
    @Test
    fun testType() {
        val type = getCmdExceptionType("congress.serving.names.shortest.first")
        val type2 = getCmdExceptionType("-getcongress.serving.names.shortest.first")
        val type3 = getCmdExceptionType("-get congressservingnamesshortestfirst")
        val type4 = getCmdExceptionType("-get congress.serving")
        val type5 = getCmdExceptionType(" -get congress.serving.names.shortest.first")
        val type6 = getCmdExceptionType("-get congress.serving.names.shortest.first")
        assertEquals(CommandExceptionType.NO_GET, type)
        assertEquals(CommandExceptionType.NO_GET, type2)
        assertEquals(CommandExceptionType.NO_DOTS, type3)
        assertEquals(CommandExceptionType.NOT_ENOUGH_PARTS, type4)
        assertEquals(CommandExceptionType.NO_GET, type5)
        assertEquals(null, type6)
    }

    @Test
    fun testValidity() {
        assertEquals(true, isValidCommand("congress.serving.names.shortest.first"))
        assertEquals(false, isValidCommand(""))
        assertEquals(false, isValidCommand("conress.serving.names.shortest.first"))
        assertEquals(false, isValidCommand("congressservingnameshortestfirst"))
        assertEquals(true, isValidCommand("congress.all.gender.prevalent"))
    }

    @Test
    fun testOptionValidity() {
        val options = autocorrect("-get conress.alll.term.longesttime")
        var valid = true
        for (i in options.indices) {
            if (!isValidCommand(options[i])) {
                valid = false
            }
        }
        assertEquals(true, valid)
    }

    @Test
    fun testAutocorrect1() {
        val options = autocorrect("-get conress.seing.names.shortest.frist")
        val options2 = autocorrect("-get conress.al.terms.longesttime")
        val options3 = autocorrect("-get conress.hsitoric.gender.prevalent")
        assertEquals(true, options.contains("congress.serving.names.shortest.first"))
        assertEquals(true, options2.contains("congress.all.terms.longest_time"))
        assertEquals(true, options3.contains("congress.historic.gender.prevalent"))
    }

    @Test
    fun testAutocorrect2() {
        val options = autocorrect("-get senaet.hsitroic.term.pop.staet")
        val options2 = autocorrect("-get houes.seving.gener.prevalnt")
        val options3 = autocorrect("-get conress.sering.current.georgai")
        assertEquals(true, options.contains("senate.historic.terms.pop.state"))
        assertEquals(true, options2.contains("house.serving.gender.prevalent"))
        assertEquals(true, options3.contains("congress.serving.current.georgia"))
    }
}

class TermsTests {
    private val data = parseCongressFile(true)
    private val data2 = parseCongressFile(false)
    private val data3 = data + data2

    @Test
    fun testTotalDays() {
        val first = getTotalDays(data[0].terms)
        val second = getTotalDays(data[34].terms)
        val third = getTotalDays(data[69].terms)
        assertEquals(11672, first)
        assertEquals(8022, second)
        assertEquals(9475, third)
    }

    @Test
    fun testMostDays() {
        val most1 = getMostDaysServed(data)
        assertEquals("Sherrod Brown", most1.person)
        assertEquals(18939, most1.time)

        val most2 = getMostDaysServed(data2)
        assertEquals("Richard Bassett", most2.person)
        assertEquals(21827, most2.time)

        val most3 = getMostDaysServed(data3)
        assertEquals("Sherrod Brown", most3.person)
        assertEquals(21827, most3.time)
    }

    @Test
    fun testMostTerms() {
        val most1 = getMostTerms(data)
        assertEquals(most1.terms, 23)
        assertEquals(most1.person, "Edward Markey")

        val most2 = getMostTerms(data2)
        assertEquals(most2.terms, 30)
        assertEquals(most2.person, "John Dingell")

        val most3 = getMostTerms(data3)
        assertEquals(most3.terms, 30)
        assertEquals(most3.person, "John Dingell")
    }

    @Test
    fun testPopularParty() {
        val popular1 = getMostPopularParty(data)
        assertEquals("Democrat", popular1.partyName)
        assertEquals(1662, popular1.appearances)

        val popular2 = getMostPopularParty(data2)
        assertEquals("Republican", popular2.partyName)
        assertEquals(17982, popular2.appearances)

        val popular3 = getMostPopularParty(data3)
        assertEquals("Democrat", popular3.partyName)
        assertEquals(20709, popular3.appearances)
    }

    @Test
    fun testPopularState() {
        val most1 = getMostPopularState(data)
        assertEquals("New Mexico", most1.state)
        assertEquals(356, most1.amount)

        val most2 = getMostPopularState(data2)
        assertEquals("North Carolina", most2.state)
        assertEquals(4035, most2.amount)

        val most3 = getMostPopularState(data3)
        assertEquals("Louisiana", most3.state)
        assertEquals(3270, most3.amount)
    }
}

class NameTest {
    private val data = parseCongressFile(true)
    private val data2 = parseCongressFile(false)
    private val data3 = data + data2

    @Test
    fun testShortestNameLengths() {
        assertEquals("Mo", getShortestFirst(data))
        assertEquals("De", getShortestFirst(data2))
        assertEquals("Mo", getShortestFirst(data3))
        assertEquals("Chu", getShortestLast(data))
        assertEquals("Wu", getShortestLast(data2))
        assertEquals("Wu", getShortestLast(data3))
    }

    @Test
    fun testLongestNameLengths() {
        assertEquals("Christopher", getLongestFirst(data))
        assertEquals("Epaphroditus", getLongestFirst(data2))
        assertEquals("Epaphroditus", getLongestFirst(data3))
        assertEquals("Cherfilus-McCormick", getLongestLast(data))
        assertEquals("O’Loughlin McCarthy", getLongestLast(data2))
        assertEquals("Cherfilus-McCormick", getLongestLast(data3))
    }
}

class TestAgeGender {
    private val data = parseCongressFile(true)
    private val data2 = parseCongressFile(false)
    private val data3 = data + data2

    @Test
    fun testAge() {
        val options1 = getMostExtremeAge(true, data)
        val options2 = getMostExtremeAge(true, data2)
        val options3 = getMostExtremeAge(true, data3)
        val options4 = getMostExtremeAge(false, data)
        assertEquals("August 01, 1995", options1)
        assertEquals("December 27, 1988", options2)
        assertEquals("August 01, 1995", options3)
        assertEquals("June 22, 1933", options4)
    }
    @Test
    fun testGender() {
        val options1 = getCommonGender(data)
        val options2 = getCommonGender(data2)
        val options3 = getCommonGender(data3)
        assertEquals(CommonGender.MALE, options1)
        assertEquals(CommonGender.MALE, options2)
        assertEquals(CommonGender.MALE, options3)
    }
}

class CurrentTests {
    private val data = parseCongressFile(true)
    private val data2 = parseCongressFile(false)
    private val data3 = data + data2
    @Test
    fun testSenate() {
        val options1 = getLegislators(data, "GA", "-get senate.serving.current.georgia")
        assertEquals(listOf(listOf("Jon Ossoff"), listOf("Raphael Warnock")), options1)

        val options2 = getLegislators(data, "OH", "-get senate.serving.current.ohio")
        assertEquals(listOf(listOf("Sherrod Brown"), listOf("Robert Portman")), options2)

        val options3 = getLegislators(data, "AL", "-get senate.serving.current.alabama")
        assertEquals(listOf(listOf("Richard Shelby"), listOf("Tommy Tuberville")), options3)
    }
}