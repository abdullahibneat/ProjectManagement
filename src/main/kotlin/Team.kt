class Team(teamName: String) {
    // Make sure name is never empty
    var name = ""
        set(value) {
            if (value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            field = value.trim()
        }

    val members = mutableListOf<Member>()

    init {
        name = teamName
    }
}