import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertTrue

class ProjectTest {
    @Test
    fun `Sample Project A is created successfully`() {
        val project = Project("Sample Project A")
        assertDoesNotThrow { project.addTask("a", 6) }
        assertDoesNotThrow { project.addTask("b", 11, 0, "a") }
        assertDoesNotThrow { project.addTask("c", 7, 0, "a") }
        assertDoesNotThrow { project.addTask("d", 3, 0, "c") }
        assertDoesNotThrow { project.addTask("e", 2, 0, "b", "d") }
    }

    @Test
    fun `Sample Project B is created successfully`() {
        val project = Project("Sample Project B")
        assertDoesNotThrow { project.addTask("a", 3) }
        assertDoesNotThrow { project.addTask("b", 4, 0, "a") }
        assertDoesNotThrow { project.addTask("c", 2, 0, "a") }
        assertDoesNotThrow { project.addTask("d", 5, 0, "b") }
        assertDoesNotThrow { project.addTask("e", 1, 0, "c") }
        assertDoesNotThrow { project.addTask("f", 2, 0, "c") }
        assertDoesNotThrow { project.addTask("g", 4, 0, "d", "e") }
        assertDoesNotThrow { project.addTask("h", 3, 0, "f", "g") }
    }

    @Test
    fun `Sample Project C is created successfully`() {
        val project = Project("Sample Project C")
        assertDoesNotThrow { project.addTask("a", 3) }
        assertDoesNotThrow { project.addTask("b", 4, 0, "a") }
        assertDoesNotThrow { project.addTask("c", 2, 0, "a") }
        assertDoesNotThrow { project.addTask("d", 5, 0, "b") }
        assertDoesNotThrow { project.addTask("e", 1, 0, "c") }
        assertDoesNotThrow { project.addTask("f", 2, 0, "c") }
        assertDoesNotThrow { project.addTask("g", 4, 0, "d", "e") }
        assertDoesNotThrow { project.addTask("h", 3, 0, "f", "g") }
    }

    @Test
    fun `Sample Project D is created successfully`() {
        val project = Project("Sample Project D")
        assertDoesNotThrow { project.addTask("a", 5) }
        assertDoesNotThrow { project.addTask("b", 12, 0, "a") }
        assertDoesNotThrow { project.addTask("c", 6, 0, "a") }
        assertDoesNotThrow { project.addTask("d", 7, 4, "c") }
        assertDoesNotThrow { project.addTask("e", 2, 0, "b") }
        assertDoesNotThrow { project.addTask("f", 3, 0, "e", "d") }
    }

    @Test
    fun `Sample Project E is created successfully`() {
        val project = Project("Sample Project E")
        assertDoesNotThrow { project.addTask("a", 2) }
        assertDoesNotThrow { project.addTask("b", 4, 0, "a") }
        assertDoesNotThrow { project.addTask("c", 11, 5,"b") }
        assertDoesNotThrow { project.addTask("d", 7) }
        assertDoesNotThrow { project.addTask("e", 13, 4, "d") }
        assertDoesNotThrow { project.addTask("f", 9, 0, "e") }
        assertDoesNotThrow { project.addTask("g", 2, 0, "d") }
        assertDoesNotThrow { project.addTask("h", 5, 3, "g") }
        assertDoesNotThrow { project.addTask("i", 6, 0, "g") }
        assertDoesNotThrow { project.addTask("j", 2, 0, "c", "f", "h", "i") }
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
    fun `A task with empty name cannot be added to a project`() {
        val project = Project("Test")
        assertThrows<Exception> { project.addTask("", 1) }
        assertThrows<Exception> { project.addTask(" ", 1) }
    }

    @Test
    fun `A task with duration of less than 1 cannot be added to a project`() {
        val project = Project("Test")
        assertThrows<Exception> { project.addTask("a", 0) }
        assertThrows<Exception> { project.addTask("b", -1) }
    }

    @Test
    fun `An end task can be deleted successfully`() {
        val project = Project("Sample Project A")
        project.addTask("a", 6)
        project.addTask("b", 11, 0, "a")
        project.addTask("c", 7, 0, "a")
        project.addTask("d", 3, 0, "c")
        project.addTask("e", 2, 0, "b", "d")
        assertDoesNotThrow { project.deleteTask("e") }
    }

    @Test
    fun `Adding task with non-existing previous task causes exception`() {
        val project = Project("Sample Project D")
        assertThrows<Exception> { project.addTask("a", 1, 0, "z") }
    }

    @Test
    fun `Deleting task with no name causes exception`() {
        val project = Project("Sample Project A")
        assertThrows<Exception> { project.deleteTask("") }
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
        project.addTask("b", 11, 0, "a")
        project.addTask("c", 7, 0, "a")
        project.addTask("d", 3, 0, "c")
        project.addTask("e", 2, 0, "b", "d")
        assertThrows<Exception> { project.deleteTask("c") }
    }

    @Test
    fun `A task's name and duration can be updated`() {
        val project = Project("Sample Project A")
        project.addTask("a", 6)
        project.addTask("b", 11, 0, "a")
        project.addTask("c", 7, 0, "a")
        project.addTask("d", 3, 0, "c")
        project.addTask("e", 2, 0, "b", "d")

        // Check that task "a" exists and task "root" does NOT exist
        assertTrue(project.tasks.find { t -> t.name === "a" } !== null)
        assertTrue(project.tasks.find { t -> t.name === "root" } == null)

        assertDoesNotThrow { project.editTask("a", "root") }

        // Check that task "a" does NOT exists anymore and task "root" exists
        assertTrue(project.tasks.find { t -> t.name === "a" } == null)
        assertTrue(project.tasks.find { t -> t.name === "root" } !== null)

        // Check that root has duration of 6
        assertTrue(project.tasks.find { t -> t.name == "root" }?.duration == 6)

        assertDoesNotThrow { project.editTask("root", newDuration = 7) }

        // Check that root duration has been updated to 7
        assertTrue(project.tasks.find { t -> t.name == "root" }?.duration == 7)
    }

    @Test
    fun `Task name cannot be changed to existing name`() {
        val project = Project("Sample Project")
        project.addTask("a", 6)
        project.addTask("b", 11, 0, "a")
        project.addTask("c", 7, 0, "a")
        project.addTask("d", 3, 0, "c")
        project.addTask("e", 2, 0, "b", "d")

        assertThrows<Exception> { project.editTask("a", "b") }
    }

    @Test
    fun `Task name cannot be changed to be empty`() {
        val project = Project("Sample Project")
        project.addTask("a", 6)
        project.addTask("b", 11, 0, "a")
        project.addTask("c", 7, 0, "a")
        project.addTask("d", 3, 0, "c")
        project.addTask("e", 2, 0, "b", "d")

        assertThrows<Exception> { project.editTask("a", "") }
        assertThrows<Exception> { project.editTask("a", " ") }
    }


    @Test
    fun `Task duration cannot be changed to be less than 1`() {
        val project = Project("Sample Project")
        project.addTask("a", 6)
        project.addTask("b", 11, 0, "a")
        project.addTask("c", 7, 0, "a")
        project.addTask("d", 3, 0, "c")
        project.addTask("e", 2, 0, "b", "d")

        assertDoesNotThrow { project.editTask("a", newDuration = 1) }
        assertThrows<Exception> { project.editTask("a", newDuration = 0) }
        assertThrows<Exception> { project.editTask("a", newDuration = -1) }
    }
}