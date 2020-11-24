class MemberJSON() {
    var name: String = ""

    constructor(name: String): this() {
        this.name = name
    }

    fun asMember() = Member(name)
}