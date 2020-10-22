class Task(var name: String, var duration: Int, val previousTasks: ArrayList<Task> = arrayListOf()) {
    val nextTasks = arrayListOf<Task>()

    init {
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