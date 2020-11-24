package Persistence

import Team

class TeamJSON() {
    var name: String = ""
    var members: List<String> = listOf()

    constructor(name: String, members: List<String>): this() {
        this.name = name
        this.members = members
    }

    fun asTeam(): Team {
        val team = Team(name)
        members.forEach { name ->
            val member = Persistence.members.find { it.name == name }
            if(member !== null) team.addMember(member)
            else println("Member $name in team ${this.name} not found.")
        }
        return team
    }
}