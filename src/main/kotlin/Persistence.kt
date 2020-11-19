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



//fun Task.toJSON(tasks: Task): taskJSON {
//    var previousTasksJson: List<String>
//    var nextTasksJson: List<String>
//
//    val NTList: List<String> = tasks.nextTasks.map { it.toString() }
//    val PTList: List<String> = tasks.previousTasks.map { it.toString() }
//
//    name = tasks.name
//    previousTasksJson = PTList
//    nextTasksJson = NTList
//    duration = tasks.duration
//    lag = tasks.lag
//
//    return taskJSON(name, previousTasksJson, nextTasksJson, duration, lag)
//}

//fun Project.toJSON(Project: Project){
//
//    var taskList: MutableList<taskJSON> = mutableListOf()
//
//    for (tasks in Project.tasks){
//
//        taskList.add(Task().toJSON(tasks))
//    }
//
//}

fun Task.toJSON() = taskJSON(name, previousTasks.map { t -> t.name }, nextTasks.map { t -> t.name }, duration, lag)
fun Project.toJSON() = projectJSON(name, tasks.map { t -> t.toJSON() } )

object Persistence{

    open fun addJSON(Project: Project) {


    }
}