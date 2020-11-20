class Member(memberName: String){
    // Make sure name is never empty
    var name = ""
        set(value) {
            if (value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            val oldName = name
            field = value.trim()
            Persistence.updateMember(oldName, this)
        }

    init {
        name = memberName
        Persistence.addMember(this)
    }
}