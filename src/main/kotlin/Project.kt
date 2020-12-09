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

    /**
     * Creates and adds a new task to this project.
     * Previous tasks is an optional vararg (you can leave it out if a task has no dependencies).
     *
     * @param name The name of this task
     * @param duration The duration of this task
     * @param lag An optional lag to delay the start of this task
     * @throws Exception if the task name is not unique to this project, or dependencies that you specify don't exists.
     */
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

    /**
     * Edit a task. Pass "null" to properties that should not be changed.
     *
     * @param name The name of the existing task
     * @param newName Set a new name for this task
     * @param newDuration Set a new duration for this task
     * @param newLag Set a new lag for this task
     * @throws Exception if: task does not exist; newName is empty or not unique; duration/lag are less than 0
     */
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

    /**
     * Deletes a task by its name.
     *
     * @param name The name of the task to be deleted
     * @throws Exception if task doesn't exist or task has successor tasks.
     */
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