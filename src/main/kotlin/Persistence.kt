import com.google.gson.Gson
import java.io.File

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

class TaskJSON() {
    var name: String = ""
    var previousTasks: List<String> = listOf()
    var nextTasks: List<String> = listOf()
    var duration: Int = 0
    var lag: Int = 0

    constructor(name: String, previousTasks: List<String>, nextTasks: List<String>, duration: Int, lag: Int): this() {
        this.name = name
        this.previousTasks = previousTasks
        this.nextTasks = nextTasks
        this.duration = duration
        this.lag = lag
    }


}

class ProjectJSON() {
    var name: String = ""
    var team: String= ""
    var tasks: List<TaskJSON> = listOf()

    constructor(name: String, team: String, tasks: List<TaskJSON>): this() {
        this.name = name
        this.team = team
        this.tasks = tasks
    }

}

class TeamJSON() {
    var name: String = ""
    var members: List<String> = listOf()

    constructor(name: String, members: List<String>): this() {
        this.name = name
        this.members = members
    }

}

class MemberJSON() {
    var name: String = ""

    constructor(name: String): this() {
        this.name = name
    }

}

fun Task.toJSON() = TaskJSON(name, previousTasks.map { t -> t.name }, nextTasks.map { t -> t.name }, duration, lag)
fun Project.toJSON() = ProjectJSON(name, team?.name ?: "", tasks.map { t -> t.toJSON() } )
fun Team.toJSON() = TeamJSON(name, members.map { m -> m.name })
fun Member.toJSON() = MemberJSON(name)

fun MemberJSON.asMember() = Member(name)

fun TeamJSON.asTeam(): Team {
    val team = Team(name)
    members.forEach { name ->
        val member = Persistence.members.find { it.name == name }
        if(member !== null) team.addMember(member)
        else println("Member $name in team ${this.name} not found.")
    }
    return team
}

fun ProjectJSON.asProject(): Project {
    val projectTeam = Persistence.teams.find { it.name == team }
    val project = Project(name, projectTeam)
    tasks.forEach { project.addTask(it.name, it.duration, *it.previousTasks.toTypedArray()) }
    return project
}

class Data() {
    var projects: List<ProjectJSON> = listOf()
    var members: List<MemberJSON> = listOf()
    var teams: List<TeamJSON> = listOf()

    constructor(projects: List<ProjectJSON>, members: List<MemberJSON>, teams: List<TeamJSON>): this() {
        this.projects = projects
        this.members = members
        this.teams = teams
    }
}

object Persistence{

    val projects = mutableListOf<Project>()
    val members = mutableListOf<Member>()
    val teams = mutableListOf<Team>()
    private var loading = true

    init {
        val file = File("data.json")
        if(file.exists()) {
            try {
                val data = Gson().fromJson(file.readText(), Data::class.java)
                data.members.forEach { it.asMember() }
                data.teams.forEach { it.asTeam() }
                data.projects.forEach { it.asProject() }
            } catch (e: Exception) {
                members.clear()
                teams.clear()
                projects.clear()
                loading = false
                throw Exception("Couldn't load data from disk, file could be corrupted.")
            }
        }
        loading = false
    }

    fun addProject(project: Project) {
        projects.add(project)
        save()
    }

    fun updateProject(name: String, project: Project) {
        projects.forEachIndexed { i, p -> if(p.name == name) projects[i] = project }
        save()
    }

    fun addTeam(team: Team) {
        teams.add(team)
        save()
    }

    fun updateTeam(name: String, team: Team) {
        teams.forEachIndexed { i, t -> if(t.name == name) teams[i] = team }
        save()
    }

    fun addMember(member: Member) {
        members.add(member)
        save()
    }

    fun updateMember(name: String, member: Member) {
        members.forEachIndexed { i, m -> if(m.name == name) members[i] = member }
        save()
    }

    fun save() {

        if (!loading) {
            val toOutput = Data(projects.map { it.toJSON() }, members.map { it.toJSON() }, teams.map { it.toJSON() })

            val jsonOutput: String = Gson().toJson(toOutput)

            File("data.json").writeText(jsonOutput)
        }
    }

    override fun toString() = """
        Members: ${members.map { it.name }}
        Teams: ${teams.map { it.name }}
        Projects: ${projects.map { "${it.name}, team: ${it.team?.name ?: "No team"}, tasks: [${it.tasks.map { t -> t.name }}]" }}
    """.trimIndent()
}