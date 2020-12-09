import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CriticalPathScalaTest {
    @Test
    def `Forward and backward passes on sample project A are correct`() {
        val sampleData = SampleDataKt.getSampleProjectA
        val tasks = sampleData.component1()
        val expected = sampleData.component2()
        val actual = CriticalPathScala.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    def `Forward and backward passes on sample project B are correct`() {
        val sampleData = SampleDataKt.getSampleProjectB
        val tasks = sampleData.component1()
        val expected = sampleData.component2()
        val actual = CriticalPathScala.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    def `Forward and backward passes on sample project C are correct`() {
        val sampleData = SampleDataKt.getSampleProjectC
        val tasks = sampleData.component1()
        val expected = sampleData.component2()
        val actual = CriticalPathScala.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    def `Forward and backward passes on sample project D are correct`() {
        val sampleData = SampleDataKt.getSampleProjectD
        val tasks = sampleData.component1()
        val expected = sampleData.component2()
        val actual = CriticalPathScala.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    def `Forward and backward passes on sample project E are correct`() {
        val sampleData = SampleDataKt.getSampleProjectE
        val tasks = sampleData.component1()
        val expected = sampleData.component2()
        val actual = CriticalPathScala.forwardBackwardPass(tasks)
        assertEquals(expected, actual)
    }

    @Test
    def `Critical path on Sample Project A is correct`() {
        val sampleData = SampleDataKt.getSampleProjectA
        val tasks = sampleData.component1()
        val criticalExpected = sampleData.component3()

        val actualCriticalPath = CriticalPathScala.findCriticalPath(tasks)
        assertEquals(criticalExpected.length, actualCriticalPath.length)
        criticalExpected.zip(actualCriticalPath).foreach(t => assertEquals(t._1, t._2))
    }

    @Test
    def `Critical path on Sample Project B is correct`() {
        val sampleData = SampleDataKt.getSampleProjectB
        val tasks = sampleData.component1()
        val criticalExpected = sampleData.component3()

        val actualCriticalPath = CriticalPathScala.findCriticalPath(tasks)
        assertEquals(criticalExpected.length, actualCriticalPath.length)
        criticalExpected.zip(actualCriticalPath).foreach(t => assertEquals(t._1, t._2))
    }

    @Test
    def `Critical path on Sample Project C is correct`() {
        val sampleData = SampleDataKt.getSampleProjectC
        val tasks = sampleData.component1()
        val criticalExpected = sampleData.component3()

        val actualCriticalPath = CriticalPathScala.findCriticalPath(tasks)
        assertEquals(criticalExpected.length, actualCriticalPath.length)
        criticalExpected.zip(actualCriticalPath).foreach(t => assertEquals(t._1, t._2))
    }

    @Test
    def `Critical path on Sample Project D is correct`() {
        val sampleData = SampleDataKt.getSampleProjectD
        val tasks = sampleData.component1()
        val criticalExpected = sampleData.component3()

        val actualCriticalPath = CriticalPathScala.findCriticalPath(tasks)
        assertEquals(criticalExpected.length, actualCriticalPath.length)
        criticalExpected.zip(actualCriticalPath).foreach(t => assertEquals(t._1, t._2))
    }

    @Test
    def `Critical path on Sample Project E is correct`() {
        val sampleData = SampleDataKt.getSampleProjectE
        val tasks = sampleData.component1()
        val criticalExpected = sampleData.component3()

        val actualCriticalPath = CriticalPathScala.findCriticalPath(tasks)
        assertEquals(criticalExpected.length, actualCriticalPath.length)
        criticalExpected.zip(actualCriticalPath).foreach(t => assertEquals(t._1, t._2))
    }
}