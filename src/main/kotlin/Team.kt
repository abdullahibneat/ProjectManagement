class Team(teamName: String) {
    // Make sure name is never empty
    var name = ""
        set(value) {
            if (value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            val oldName = name
            field = value.trim()
            Persistence.updateTeam(oldName, this)
        }

    val members = mutableListOf<Member>()

    init {
        name = teamName
        Persistence.addTeam(this)
    }

    fun addMember(member: Member) {
        if(members.contains(member)) throw Exception("Member is already part of $name team")
        else {
            members.add(member)
            Persistence.updateTeam(name, this)
        }
    }
}