import java.io.File
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class taskJSON(
        var name: String,
        var PreviousTasks: List<String>,
        var nextTasks: List<String>,
        var duration: Int,
        var lag: Int
)


class projectJSON(
        val name: String,
        var taskList: List<taskJSON>
)



fun Task.toJSON() = taskJSON(name, previousTasks.map { t -> t.name }, nextTasks.map { t -> t.name }, duration, lag)
fun Project.toJSON() = projectJSON(name, tasks.map { t -> t.toJSON() } )

object Persistence{

    var ProjectList = mutableListOf<projectJSON>()

    open fun addJSON(projects: Project) {

        ProjectList.add(projects.toJSON())


    }

    open fun saveJSON(){


        val jsonFormatted = GsonBuilder().setPrettyPrinting().create()

        val jsonOutput: String = jsonFormatted.toJson(ProjectList.toList())

        File("Project.json").writeText(jsonOutput)

    }
}