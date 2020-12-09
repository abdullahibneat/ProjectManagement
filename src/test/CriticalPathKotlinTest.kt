import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CriticalPathKotlinTest {
    @Test
    fun `Forward and backward passes on sample project A are correct`() {
        val (tasks, expected) = getSampleProjectA()
        val actual = CriticalPathKotlin.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    fun `Forward and backward passes on sample project B are correct`() {
        val (tasks, expected) = getSampleProjectB()
        val actual = CriticalPathKotlin.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    fun `Forward and backward passes on sample project C are correct`() {
        val (tasks, expected) = getSampleProjectC()
        val actual = CriticalPathKotlin.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    fun `Forward and backward passes on sample project D are correct`() {
        val (tasks, expected) = getSampleProjectD()
        val actual = CriticalPathKotlin.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    fun `Forward and backward passes on sample project E are correct`() {
        val (tasks, expected) = getSampleProjectE()
        val actual = CriticalPathKotlin.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    fun `Critical path on Sample Project A is correct`() {
        val (tasks, _, criticalExpected) = getSampleProjectA()
        val actualCriticalPath = CriticalPathKotlin.findCriticalPath(tasks)
        assertEquals(criticalExpected.size, actualCriticalPath.size)
        criticalExpected.forEachIndexed { i, t -> assertEquals(t, actualCriticalPath.elementAt(i)) }
    }

    @Test
    fun `Critical path on Sample Project B is correct`() {
        val (tasks, _, criticalExpected) = getSampleProjectB()
        val actualCriticalPath = CriticalPathKotlin.findCriticalPath(tasks)
        assertEquals(criticalExpected.size, actualCriticalPath.size)
        criticalExpected.forEachIndexed { i, t -> assertEquals(t, actualCriticalPath.elementAt(i)) }
    }

    @Test
    fun `Critical path on Sample Project C is correct`() {
        val (tasks, _, criticalExpected) = getSampleProjectC()
        val actualCriticalPath = CriticalPathKotlin.findCriticalPath(tasks)
        assertEquals(criticalExpected.size, actualCriticalPath.size)
        criticalExpected.forEachIndexed { i, t -> assertEquals(t, actualCriticalPath.elementAt(i)) }
    }

    @Test
    fun `Critical path on Sample Project D is correct`() {
        val (tasks, _, criticalExpected) = getSampleProjectD()
        val actualCriticalPath = CriticalPathKotlin.findCriticalPath(tasks)
        assertEquals(criticalExpected.size, actualCriticalPath.size)
        criticalExpected.forEachIndexed { i, t -> assertEquals(t, actualCriticalPath.elementAt(i)) }
    }

    @Test
    fun `Critical path on Sample Project E is correct`() {
        val (tasks, _, criticalExpected) = getSampleProjectE()
        val actualCriticalPath = CriticalPathKotlin.findCriticalPath(tasks)
        assertEquals(criticalExpected.size, actualCriticalPath.size)
        criticalExpected.forEachIndexed { i, t -> assertEquals(t, actualCriticalPath.elementAt(i)) }
    }
}