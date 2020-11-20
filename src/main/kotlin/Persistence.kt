import java.io.File
import com.google.gson.GsonBuilder

data class TaskJSON(
        val name: String,
        val PreviousTasks: List<String>,
        val nextTasks: List<String>,
        val duration: Int,
        val lag: Int
)

data class ProjectJSON(
        val name: String,
        val taskList: List<TaskJSON>
)

fun Task.toJSON() = TaskJSON(name, previousTasks.map { t -> t.name }, nextTasks.map { t -> t.name }, duration, lag)
fun Project.toJSON() = ProjectJSON(name, tasks.map { t -> t.toJSON() } )

object Persistence{

    val projects = mutableListOf<ProjectJSON>()

    open fun addJSON(projects: Project) {

        this.projects.add(projects.toJSON())


    }

    open fun saveJSON(){

        val jsonFormatted = GsonBuilder().setPrettyPrinting().create()

        val jsonOutput: String = jsonFormatted.toJson(projects.toList())

        File("Data.json").writeText(jsonOutput)

    }
}