import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertTrue

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
        project.addTask("b", 11, "a")
        project.addTask("c", 7, "a")
        project.addTask("d", 3, "c")
        project.addTask("e", 2, "b", "d")
        assertThrows<Exception> { project.deleteTask("c") }
    }

    @Test
    fun `A task's name and duration can be updated`() {
        val project = Project("Sample Project A")
        project.addTask("a", 6)
        project.addTask("b", 11, "a")
        project.addTask("c", 7, "a")
        project.addTask("d", 3, "c")
        project.addTask("e", 2, "b", "d")

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
        project.addTask("b", 11, "a")
        project.addTask("c", 7, "a")
        project.addTask("d", 3, "c")
        project.addTask("e", 2, "b", "d")

        assertThrows<Exception> { project.editTask("a", "b") }
    }

    @Test
    fun `Task name cannot be changed to be empty`() {
        val project = Project("Sample Project")
        project.addTask("a", 6)
        project.addTask("b", 11, "a")
        project.addTask("c", 7, "a")
        project.addTask("d", 3, "c")
        project.addTask("e", 2, "b", "d")

        assertThrows<Exception> { project.editTask("a", "") }
        assertThrows<Exception> { project.editTask("a", " ") }
    }


    @Test
    fun `Task duration cannot be changed to be less than 1`() {
        val project = Project("Sample Project")
        project.addTask("a", 6)
        project.addTask("b", 11, "a")
        project.addTask("c", 7, "a")
        project.addTask("d", 3, "c")
        project.addTask("e", 2, "b", "d")

        assertDoesNotThrow { project.editTask("a", newDuration = 1) }
        assertThrows<Exception> { project.editTask("a", newDuration = 0) }
        assertThrows<Exception> { project.editTask("a", newDuration = -1) }
    }
}