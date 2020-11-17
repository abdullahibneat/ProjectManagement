import com.google.gson.Gson
import com.google.gson.GsonBuilder

class TaskJSON(
        var name: String,
        var PreviousTasks: List<String>,
        var NextTasks: List<String>,
        var duration: Int,
        var lag: Int
){
    fun toJSON(tasks: Task){

        val NTList:List<String> = tasks.nextTasks.map { it.toString() }

        name = tasks.name
        NextTasks = NTList
        duration = tasks.duration
        lag = tasks.lag
    }
}

class ProjectJSON(
        val name: String,
        val task: List<TaskJSON>
){
    fun toJSON(Project: Project){



    }

}

object Persistence{

    open fun addJSON(Project: Project) {


    }
}