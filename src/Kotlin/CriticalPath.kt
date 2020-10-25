fun main() {
    val a = Task("a", 6)
    val b = Task("b", 11, arrayListOf(a))
    val c = Task("c", 7, arrayListOf(a))
    val d = Task("d", 3, arrayListOf(c))
    val e = Task("e", 2, arrayListOf(b, d))

    val allTasks = arrayOf(a, b, c, d, e)
    println(forwardBackwardPass(allTasks.toSet()))

}

data class Calculations(var earlyStart: Int, var earlyFinish: Int, var lateStart: Int? = null, var lateFinish: Int? = null, var float: Int? = null ,var onCriticalPath: Boolean? = false) {}

fun forwardBackwardPass(tasks: Set<Task>): HashMap<Task, Calculations> {
    val computed = HashMap<Task, Calculations>()
    val toCompute = tasks.toMutableList()

    var forwardPass = true // Begin with forward pass

    while(toCompute.isNotEmpty()) {
        val current = toCompute.removeAt(0)

        val expectedNumberOfDependencies = if(forwardPass) current.previousTasks.size else current.nextTasks.size
        val dependencies = arrayListOf<Calculations>()

        // Try to find all previous/successor tasks that have been already computed
        if(forwardPass) for(t in current.previousTasks) computed[t]?.let { dependencies.add(it) } ?: break
        else for(t in current.nextTasks) computed[t]?.let { if(it.lateStart !== null && it.lateFinish !== null) dependencies.add(it) } ?: break

        if(dependencies.size != expectedNumberOfDependencies) {
            toCompute.add(current) // This task cannot be computed now, try later.
            continue
        }

        if(forwardPass) {
            // Forward pass requires previous tasks' early finish times.
            // NOTE: Since the starting task (A) has no dependencies, set the value to 0.
            // For the current task:
            //  - the early start time is calculated by adding 1 to the whichever dependant task
            //    will finish last.
            //  - the early finish time is computed with the equation: this.earlyStart + this.duration - 1
            //                              This can be simplified: = (prevEarlyFinish + 1) + this.duration - 1 (cancel out the 1s: 1-1=0)
            //                                                      = prevEarlyFinish + this.duration

            val prevEarlyFinish = if (dependencies.size > 0) dependencies.maxOf { t -> t.earlyFinish } else 0
            computed[current] = Calculations(prevEarlyFinish + 1, prevEarlyFinish + current.duration)

            if(current.nextTasks.isEmpty() && computed.contains(current)) forwardPass = false   // If this is the END task (no successor tasks)
                                                                                                // AND it's early start and finish values have been computed, forward pass is completed.

            toCompute.add(current) // Add task to end of list to compute the backward pass.
        } else {
            // Backward pass requires the successor tasks' late start times.
            // NOTE: Since the very last task has no successor tasks, set the value to: earlyFinish + 1
            // For the current task:
            //  - the late finish time is calculated by subtracting 1 from whichever successor task
            //    finishes first.
            //  - the late start time is computed with the equation: this.lateFinish - this.duration + 1
            //    Like in the forward pass, this is simplified to:   nextLateStart - current.duration

            val nextLateStart = if(dependencies.size > 0 ) dependencies.minOf { t -> t.lateStart!! } else computed[current]!!.earlyFinish + 1

            computed[current]!!.apply {
                lateFinish = nextLateStart - 1
                lateStart = nextLateStart - current.duration
                float = lateFinish!! - earlyFinish
                onCriticalPath = float == 0
            }
        }
    }

    return computed
}