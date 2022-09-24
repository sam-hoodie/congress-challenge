import kotlin.test.Test
import kotlin.test.assertEquals

class Tests {
    @Test
    fun testAutocorrect1() {
        val options1 = provideAutocorrectOptions("-get sneate.serving.terms.pop.state", 0)
        assertEquals(1, options1.size)
        assertEquals("-get senate.serving.terms.pop.state", options1[0])
    }

    @Test
    fun testAutocorrect2() {
        // Autocorrect for a valid query should return "same" as the result
        val options1 = provideAutocorrectOptions("-get senate.serving.terms.longest_time", 3)
        assertEquals(1, options1.size)
        assertEquals("same", options1[0])
    }
}