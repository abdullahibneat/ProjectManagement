import Persistence.Persistence

class Team(teamName: String) {
    // Make sure name is never empty
    var name = ""
        set(value) {
            if (value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            if(value.trim() !== name && Persistence.teams.find { it.name == value.trim() } !== null) throw Exception("Team name already in use.")
            field = value.trim()
            Persistence.save()
        }

    val members = mutableListOf<Member>()

    init {
        name = teamName
        Persistence.addTeam(this)
    }

    /**
     * Add a member to this team
     *
     * @param member The member to be added
     * @throws Exception if member is already part of the team.
     */
    fun addMember(member: Member) {
        if(members.contains(member)) throw Exception("Member is already part of $name team")
        else {
            members.add(member)
            Persistence.save()
        }
    }

    override fun toString() = name + ""
}