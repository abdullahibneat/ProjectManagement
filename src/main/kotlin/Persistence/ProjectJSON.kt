package Persistence

import Project

class ProjectJSON() {
    var name: String = ""
    var team: String= ""
    var tasks: List<TaskJSON> = listOf()

    constructor(name: String, team: String, tasks: List<TaskJSON>): this() {
        this.name = name
        this.team = team
        this.tasks = tasks
    }

    fun asProject(): Project {
        val projectTeam = Persistence.teams.find { it.name == team }
        val project = Project(name, projectTeam)
        tasks.forEach { project.addTask(it.name, it.duration, it.lag, *it.previousTasks.toTypedArray()) }
        return project
    }
}