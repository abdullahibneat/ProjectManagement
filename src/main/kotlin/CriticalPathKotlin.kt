fun main() {
    val a = Task("a", 6)
    val b = Task("b", 11, mutableSetOf(a))
    val c = Task("c", 7, mutableSetOf(a))
    val d = Task("d", 3, mutableSetOf(c), 2)
    val e = Task("e", 2, mutableSetOf(b, d))

    val allTasks = setOf(a, b, c, d, e)
    println(CriticalPathKotlin.forwardBackwardPass(allTasks))
    println(CriticalPathKotlin.findCriticalPath(allTasks).toList()) // Use toList to print out task names
}

object CriticalPathKotlin: CriticalPath {
    override fun forwardBackwardPass(tasks: Set<Task>): HashMap<Task, CriticalCalculations> {
        val numberOfTasks = tasks.size
        val computed = HashMap<Task, CriticalCalculations>()
        val toCompute = tasks.toMutableList()

        var forwardPass = true // Begin with forward pass
        var highestEarlyFinish = 0 // Keep track of task that has highest early finish
        // Used for whenever there are multiple end tasks (e.g. sampleProjectC).

        while(toCompute.isNotEmpty()) {
            val current = toCompute.removeAt(0)

            val expectedNumberOfDependencies = if(forwardPass) current.previousTasks.size else current.nextTasks.size
            val dependencies = mutableSetOf<CriticalCalculations>()

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
                //  - the early start time is computed with the equation: prev.earlyFinish + this.lag + 1
                //  - the early finish time is computed with the equation: this.earlyStart + this.duration - 1

                val prevEarlyFinish = if (dependencies.size > 0) dependencies.maxOf { t -> t.earlyFinish } else 0
                val currentEarlyStart = prevEarlyFinish + current.lag + 1
                val currentEarlyFinish = currentEarlyStart + current.duration - 1

                computed[current] = CriticalCalculations(currentEarlyStart, currentEarlyFinish)

                if(currentEarlyFinish > highestEarlyFinish) highestEarlyFinish = currentEarlyFinish

                if(computed.size == numberOfTasks) forwardPass = false // If earlyStart and earlyFinish for ALL tasks have been computed, forward pass is completed.

                toCompute.add(current) // Add task to end of list to compute the backward pass.
            } else {
                // Backward pass requires the successor tasks' late start times.
                // NOTE: Since the very last task(s) has no successor tasks, set the value to: HIGHEST earlyFinish + 1 (refer to var highestEarlyFinish above)
                // For the current task:
                //  - the late finish time is computed with the equation: next.lateStart - next.lag - 1
                //  - the late start time is computed with the equation: this.lateFinish - this.duration + 1

                val nextTask = current.nextTasks.minByOrNull { t -> computed[t]?.lateStart!! }
                val nextLateStart = computed[nextTask]?.lateStart ?: highestEarlyFinish + 1

                val currentLateFinish = nextLateStart - (nextTask?.lag ?: 0) - 1

                computed[current]!!.apply {
                    lateFinish = currentLateFinish
                    lateStart = currentLateFinish - current.duration + 1
                    float = currentLateFinish - earlyFinish
                }
            }
        }

        return computed
    }

    override fun findCriticalPath(tasks: Set<Task>): Array<Task> {
        val computedValues = forwardBackwardPass(tasks)

        var current = computedValues.keys.find { t -> t.previousTasks.isEmpty() && computedValues[t]?.float == 0 } ?: return arrayOf() // Find the starting task, if not found return empty set.

        val criticalTasks = mutableListOf(current)
        val error = criticalTasks.isEmpty() // An error is indicated by the set being empty.

        while (!error && current.nextTasks.isNotEmpty()) // current.nextTasks.isNotEmpty() stops the while loop when end task is reached
            current.nextTasks.find { t -> computedValues[t]?.float == 0 }
                    ?.let { criticalTasks.add(it); current = it } // If next critical task is found, add it to set...
                    ?: criticalTasks.clear() // Otherwise clear the set

        return criticalTasks.toTypedArray()
    }
}
