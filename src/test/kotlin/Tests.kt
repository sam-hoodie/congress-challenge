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
        assertEquals(true, options.contains("senate.historic.terms.pop.state"))
        assertEquals(true, options2.contains("house.serving.gender.prevalent"))
    }
}
