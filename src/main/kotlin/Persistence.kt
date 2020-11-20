import java.io.File
import com.google.gson.GsonBuilder

class TaskJSON(
        var name: String,
        var PreviousTasks: List<String>,
        var nextTasks: List<String>,
        var duration: Int,
        var lag: Int
)


class ProjectJSON(
        val name: String,
        var taskList: List<TaskJSON>
)



fun Task.toJSON() = TaskJSON(name, previousTasks.map { t -> t.name }, nextTasks.map { t -> t.name }, duration, lag)
fun Project.toJSON() = ProjectJSON(name, tasks.map { t -> t.toJSON() } )

object Persistence{

    var ProjectList = mutableListOf<ProjectJSON>()

    open fun addJSON(projects: Project) {

        ProjectList.add(projects.toJSON())


    }

    open fun saveJSON(){

        val jsonFormatted = GsonBuilder().setPrettyPrinting().create()

        val jsonOutput: String = jsonFormatted.toJson(ProjectList.toList())

        File("Data.json").writeText(jsonOutput)

    }
}