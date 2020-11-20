class Project(projectName: String) {
    // Make sure name is never empty
    var name = ""
        set(value) {
            if(value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            field = value.trim()
        }

    val tasks = mutableSetOf<Task>()

    init {
        name = projectName
        Persistence.addProject(this)
    }

    fun addTask(name: String, duration: Int, vararg previousTasks: String) {
        var newTask = Task(name, duration) // Create task here to perform error checking

        if(tasks.find { t -> t.name === newTask.name } !== null)
            throw Exception("Task name must be unique")

        val dependencies = tasks.filter { t -> previousTasks.contains(t.name) }

        if(dependencies.size != previousTasks.size)
            throw Exception("Some or all dependent tasks do not exist")

        newTask = Task(name, duration, dependencies.toMutableSet())

        tasks.add(newTask)
        Persistence.updateProject(this)
    }

    fun editTask(name: String, newName: String? = null, newDuration: Int? = null) {
        val trimmedName = name.trim()

        if(trimmedName.isEmpty())
            throw Exception("Task name cannot be empty")

        val task = tasks.find { t -> t.name === trimmedName }
                ?: throw Exception("Task does not exist")

        // If changing name, check if name is already in use
        if(newName !== null && tasks.find { t -> t.name == newName.trim() } !== null)
            throw Exception("Task name must be unique")

        val originalName = task.name
        val originalDuration = task.duration

        // Try setting new values, if exception occurs revert changes.
        try {
            if(newName !== null) task.name = newName
            if(newDuration !== null) task.duration = newDuration
        } catch (e: Exception) {
            task.name = originalName
            task.duration = originalDuration
            throw Exception("Invalid name or duration.")
        }
        Persistence.updateProject(this)
    }

    fun deleteTask(name: String) {
        val trimmedName = name.trim()

        if(trimmedName.isEmpty())
            throw Exception("Task name cannot be empty")

        val task = tasks.find { t -> t.name === trimmedName }
                ?: throw Exception("Task does not exist")

        if(task.nextTasks.isNotEmpty())
            throw Exception("Other tasks depend on this task. Delete them first")

        task.previousTasks.forEach { t -> t.nextTasks.remove(task) }

        tasks.remove(task)
        Persistence.updateProject(this)
    }
}