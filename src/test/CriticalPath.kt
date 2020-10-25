import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CriticalPath {
    @Test
    fun `Forward and backward passes on sample project A are correct`() {
        val (tasks, expected) = getSampleProjectA()
        val actual = forwardBackwardPass(tasks.toSet())
        assertEquals(expected, actual)
    }

    @Test
    fun `Forward and backward passes on sample project B are correct`() {
        val (tasks, expected) = getSampleProjectB()
        val actual = forwardBackwardPass(tasks.toSet())
        assertEquals(expected, actual)
    }

    @Test
    fun `Forward and backward passes on sample project C are correct`() {
        val (tasks, expected) = getSampleProjectC()
        val actual = forwardBackwardPass(tasks.toSet())
        assertEquals(expected, actual)
    }
}