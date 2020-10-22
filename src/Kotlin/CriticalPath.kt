data class Calculations(var earlyStart: Int, var earlyFinish: Int) {}

fun criticalPath(tasks: Set<Task>) {
    val computed = HashMap<Task, Calculations>()
    val toCompute = tasks.toMutableList()

    // Forward pass
    while(toCompute.isNotEmpty()) {
        val next = toCompute[0]

        // Try to find all previous tasks that have been computed
        val dependencies = arrayListOf<Calculations>()
        for(t in next.previousTasks) computed[t]?.let { dependencies.add(it) }

        // If all previous tasks have been already calculated, this task can now be processed
        if(dependencies.size == next.previousTasks.size) {
            val prevEarlyFinish = if(dependencies.size > 0 ) dependencies.maxOf { t -> t.earlyFinish } else 0
            computed[next] = Calculations(prevEarlyFinish + 1, prevEarlyFinish + next.duration)
            toCompute.removeAt(0)
        }
    }
    println(computed)
}