import com.sun.org.apache.xpath.internal.operations.Equals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

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

    @Test
    fun `CriticalPath test passes on sample project A are correct`() {
        val (tasks, _, criticalExpected) = getSampleProjectA()
        val actualCriticalPath = findCriticalPath(tasks.toSet())
        assertTrue { criticalExpected.contentEquals(actualCriticalPath.toArray()) }
    }

    @Test
    fun `CriticalPath test passes on sample project B are correct`() {
        val (tasks, _, criticalExpected) = getSampleProjectB()
        val actualCriticalPath = findCriticalPath(tasks.toSet())
        assertTrue { criticalExpected.contentEquals(actualCriticalPath.toArray()) }
    }

    @Test
    fun `CriticalPath test passes on sample project C are correct`() {
        val (tasks, _, criticalExpected) = getSampleProjectC()
        val actualCriticalPath = findCriticalPath(tasks.toSet())
        assertTrue { criticalExpected.contentEquals(actualCriticalPath.toArray()) }
    }



}