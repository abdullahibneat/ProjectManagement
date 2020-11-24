import Persistence.Persistence

class Member(memberName: String){
    // Make sure name is never empty
    var name = ""
        set(value) {
            if (value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            if(value.trim() !== name && Persistence.members.find { it.name == value.trim() } !== null) throw Exception("Member with this name exists already.")
            field = value.trim()
            Persistence.save()
        }

    init {
        name = memberName
        Persistence.addMember(this)
    }
}