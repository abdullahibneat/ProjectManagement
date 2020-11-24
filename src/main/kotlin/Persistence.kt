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

fun Task.toJSON() = TaskJSON(name, previousTasks.map { t -> t.name }, nextTasks.map { t -> t.name }, duration, lag)
fun Project.toJSON() = ProjectJSON(name, team?.name ?: "", tasks.map { t -> t.toJSON() } )
fun Team.toJSON() = TeamJSON(name, members.map { m -> m.name })
fun Member.toJSON() = MemberJSON(name)

object Persistence{
    val projects = mutableListOf<Project>()
    val members = mutableListOf<Member>()
    val teams = mutableListOf<Team>()
    private var loading = true

    // Load data from JSON file if it exists
    init {
        val file = File("data.json")
        if(file.exists()) {
            try {
                val data = Gson().fromJson(file.readText(), Data::class.java)
                // Load data into lists. Using as*() methods adds them to Persistence directly
                // (refer to Member, Team and Project constructors)
                data.members.forEach { it.asMember() }
                data.teams.forEach { it.asTeam() }
                data.projects.forEach { it.asProject() }
            } catch (e: Exception) {
                // If any error is encountered, clear all lists and throw exception.
                // E.g. couldn't parse JSON file
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
        // Find the member by its name, and replace it in the list
        members.forEachIndexed { i, m -> if(m.name == name) members[i] = member }
        save()
    }

    private fun save() {
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