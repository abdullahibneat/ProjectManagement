package Persistence

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