class Task(taskName: String, var duration: Int, val previousTasks: MutableSet<Task> = mutableSetOf()) {
    // Make sure name is never empty
    var name = ""
        set(value) {
            if(value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            field = value.trim()
        }

    val nextTasks = mutableSetOf<Task>()

    init {
        name = taskName

        if(duration < 1)
            throw Exception("Task must have a duration greater than 1")

        previousTasks.forEach { t -> t.nextTasks.add(this) }
    }

    override fun toString() = name

//    override fun toString() = """
//
//        Name: $name
//        Duration: $duration
//        Previous tasks: [${previousTasks.map { t -> t.name }.joinToString()}]
//        Next tasks: [${nextTasks.map { t -> t.name }.joinToString()}]
//
//    """.trimIndent()
}