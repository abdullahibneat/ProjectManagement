import java.io.File
import com.google.gson.GsonBuilder

fun main() {
    val team = Team("0+0=0")
    team.addMember(Member("Haren"))

    val project = Project("Sample Project", team)
    project.addTask("a", 6)
    project.addTask("b", 11, "a")
    project.addTask("c", 7, "a")
    project.addTask("d", 3, "c")

    team.addMember(Member("Abdullah"))

    project.addTask("e", 2, "b", "d")

    val project2 = Project("Sample Project 2")
    project2.addTask("a 2", 6)
    project2.addTask("b 2", 11, "a 2")
    project2.addTask("c 2", 7, "a 2")
    project2.addTask("d 2", 3, "c 2")
    project2.addTask("e 2", 2, "b 2", "d 2")
}

data class TaskJSON(
        val name: String,
        val previousTasks: List<String>,
        val nextTasks: List<String>,
        val duration: Int,
        val lag: Int
)

data class ProjectJSON(
        val name: String,
        val team: String,
        val tasks: List<TaskJSON>
)

data class TeamJSON(
        val name: String,
        val members: List<String>
)

fun Task.toJSON() = TaskJSON(name, previousTasks.map { t -> t.name }, nextTasks.map { t -> t.name }, duration, lag)
fun Project.toJSON() = ProjectJSON(name, team?.name ?: "", tasks.map { t -> t.toJSON() } )
fun Team.toJSON() = TeamJSON(name, members.map { m -> m.name })

object Persistence{

    val projects = mutableListOf<ProjectJSON>()
    val members = mutableListOf<Member>()
    val teams = mutableListOf<Team>()

    fun addProject(project: Project) {
        projects.add(project.toJSON())
        save()
    }

    fun updateProject(project: Project) {
        projects.forEachIndexed { i, p -> if(p.name == project.name) projects[i] = project.toJSON() }
        save()
    }

    fun addTeam(team: Team) {
        teams.add(team)
        save()
    }

    fun updateTeam(team: Team) {
        teams.forEachIndexed { i, t -> if(t.name == team.name) teams[i] = t }
        save()
    }

    fun addMember(member: Member) {
        members.add(member)
        save()
    }

    fun updateMember(member: Member) {
        members.forEachIndexed { i, m -> if(m.name == member.name) members[i] = m }
        save()
    }

    fun save() {
        val jsonFormatted = GsonBuilder().setPrettyPrinting().create()

        val toOutput = mapOf("projects" to projects.toList(), "members" to members.toList(), "teams" to teams.map { it.toJSON() }.toList())

        val jsonOutput: String = jsonFormatted.toJson(toOutput)

        File("data.json").writeText(jsonOutput)

    }
}