import Persistence.Persistence

class Project(projectName: String, var team: Team? = null) {
    // Make sure name is never empty
    var name = ""
        set(value) {
            if(value.trim().isEmpty()) throw Exception("Name cannot be empty.")
            field = value.trim()
            Persistence.save()
        }

    val tasks = mutableSetOf<Task>()

    init {
        name = projectName
        Persistence.addProject(this)
    }

    fun addTask(name: String, duration: Int, lag: Int = 0, vararg previousTasks: String) {
        if(tasks.find { t -> t.name === name } !== null)
            throw Exception("Task name must be unique")

        val dependencies = tasks.filter { t -> previousTasks.contains(t.name) }

        if(dependencies.size != previousTasks.size)
            throw Exception("Some or all dependent tasks do not exist")

        val newTask = Task(name, duration, dependencies.toMutableSet(), lag)

        tasks.add(newTask)
        Persistence.save()
    }

    fun editTask(name: String, newName: String? = null, newDuration: Int? = null, newLag: Int? = null) {
        val trimmedName = name.trim()

        if(trimmedName.isEmpty())
            throw Exception("Task name cannot be empty")

        val task = tasks.find { t -> t.name === trimmedName }
                ?: throw Exception("Task does not exist")

        // Change properties if they have been set
        if(newName !== null) {
            if(tasks.find { it.name == newName.trim() } !== null)
                throw Exception("Another task is using this name already.")
            task.name = newName
        }
        if(newDuration !== null) task.duration = newDuration
        if(newLag !== null) task.lag = newLag

        Persistence.save()
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
        Persistence.save()
    }
}