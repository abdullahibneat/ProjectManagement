import com.google.gson.Gson
import com.google.gson.GsonBuilder

class TaskJSON(
        val name: String,
        val PreviousTasks: List<String>,
        val NextTasks: List<String>,
        val duration: Int,
        val lag: Int
){

}

class ProjectJSON(
        val name: String,
        val tasks: TaskJSON
){

}