class Member(memberName: String){
    // Make sure name is never empty
    var name = ""
        set(value) {
            if (value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            field = value.trim()
        }


    init {
        name = memberName
    }
}