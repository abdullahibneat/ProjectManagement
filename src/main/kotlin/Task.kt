class Task(taskName: String, taskDuration: Int, val previousTasks: MutableSet<Task> = mutableSetOf(), Lag: Int = 0) {
    // Make sure name is never empty
    var name = ""
        set(value) {
            if(value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            field = value.trim()
        }
    var duration = 0
        set(value) {
            if(value < 1) throw Exception("Task must have a duration greater than 1")
            field = value
        }

    var lag = 0


    val nextTasks = mutableSetOf<Task>()

    init {
        name = taskName
        duration = taskDuration


        previousTasks.forEach { t -> t.nextTasks.add(this) }

        lag = Lag
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