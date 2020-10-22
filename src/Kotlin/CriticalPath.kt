fun main() {
    val a = Task("a", 6)
    val b = Task("b", 11, arrayListOf(a))
    val c = Task("c", 7, arrayListOf(a))
    val d = Task("d", 3, arrayListOf(c))
    val e = Task("e", 2, arrayListOf(b, d))

    val allTasks = arrayOf(a, b, c, d, e)
    criticalPath(allTasks.toSet())
}

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
        } else {
            // This task cannot be computed now, move on to next task by moving this task to the end of the array.
            toCompute.removeAt(0)
            toCompute.add(next)
        }
    }
    println(computed)
}