import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ProjectTest {
    @Test
    fun `Sample Project A is created successfully`() {
        val project = Project("Sample Project A")
        assertDoesNotThrow { project.addTask("a", 6) }
        assertDoesNotThrow { project.addTask("b", 11, "a") }
        assertDoesNotThrow { project.addTask("c", 7, "a") }
        assertDoesNotThrow { project.addTask("d", 3, "c") }
        assertDoesNotThrow { project.addTask("e", 2, "b", "d") }
    }

    @Test
    fun `Sample Project B is created successfully`() {
        val project = Project("Sample Project B")
        assertDoesNotThrow { project.addTask("a", 3) }
        assertDoesNotThrow { project.addTask("b", 4, "a") }
        assertDoesNotThrow { project.addTask("c", 2, "a") }
        assertDoesNotThrow { project.addTask("d", 5, "b") }
        assertDoesNotThrow { project.addTask("e", 1, "c") }
        assertDoesNotThrow { project.addTask("f", 2, "c") }
        assertDoesNotThrow { project.addTask("g", 4, "d", "e") }
        assertDoesNotThrow { project.addTask("h", 3, "f", "g") }
    }

    @Test
    fun `Sample Project C is created successfully`() {
        val project = Project("Sample Project C")
        assertDoesNotThrow { project.addTask("a", 3) }
        assertDoesNotThrow { project.addTask("b", 4, "a") }
        assertDoesNotThrow { project.addTask("c", 2, "a") }
        assertDoesNotThrow { project.addTask("d", 5, "b") }
        assertDoesNotThrow { project.addTask("e", 1, "c") }
        assertDoesNotThrow { project.addTask("f", 2, "c") }
        assertDoesNotThrow { project.addTask("g", 4, "d", "e") }
        assertDoesNotThrow { project.addTask("h", 3, "f", "g") }
    }

    @Test
    fun `A project with no name cannot be created`() {
        assertThrows<Exception> { Project("") }
        assertThrows<Exception> { Project(" ") }
    }

    @Test
    fun `A project's name cannot be set to empty after creation`() {
        val project = Project("Test")
        assertThrows<Exception> { project.name = "" }
        assertThrows<Exception> { project.name = " " }
    }

    @Test
    fun `An end task can be deleted successfully`() {
        val project = Project("Sample Project A")
        project.addTask("a", 6)
        project.addTask("b", 11, "a")
        project.addTask("c", 7, "a")
        project.addTask("d", 3, "c")
        project.addTask("e", 2, "b", "d")
        assertDoesNotThrow { project.deleteTask("e") }
    }

    @Test
    fun `Adding task with non-existing previous task causes exception`() {
        val project = Project("Sample Project D")
        assertThrows<Exception> { project.addTask("a", 1, "z") }
    }

    @Test
    fun `Deleting non-existing task causes exception`() {
        val project = Project("Sample Project A")
        assertThrows<Exception> { project.deleteTask("z") }
    }

    @Test
    fun `Deleting a task in the middle causes exception`() {
        val project = Project("Sample Project A")
        project.addTask("a", 6)
        project.addTask("b", 11, "a")
        project.addTask("c", 7, "a")
        project.addTask("d", 3, "c")
        project.addTask("e", 2, "b", "d")
        assertThrows<Exception> { project.deleteTask("c") }
    }
}