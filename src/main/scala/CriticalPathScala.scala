import java.util.{Map => JMap, Set => JSet}

import scala.jdk.CollectionConverters.{MapHasAsJava, MapHasAsScala, SetHasAsJava, SetHasAsScala}

object CriticalPathScala extends CriticalPath {
    def main(args: Array[String]): Unit = {
        val a = new Task("a", 6, Set[Task]().asJava, 0)
        val b = new Task("b", 11, Set(a).asJava, 0)
        val c = new Task("c", 7, Set(a).asJava, 0)
        val d = new Task("d", 3, Set(c).asJava, 2)
        val e = new Task("e", 2, Set(b, d).asJava, 0)

        val allTasks = Set(a, b, c, d, e).asJava

        println(findCriticalPath(allTasks))
    }

    def forwardPass(task: Task, calculations: Map[Task, CriticalCalculations] = Map(), single: Boolean = false): Map[Task, CriticalCalculations] = {
        // Find all computed and not-yet-computed dependencies
        val dependencies = {
            val prevTasks = task.getPreviousTasks.asScala

            if(prevTasks.isEmpty) Map[Task, CriticalCalculations]() // No dependencies, i.e. root task, return empty map

            else {
                val prevTasksComputed = task.getPreviousTasks.asScala
                    .filter(t => calculations.contains(t))
                    .map(t => Map(t -> calculations(t)))

                prevTasks
                    .filter(t => !calculations.contains(t)) // Find all non-computed dependencies
                    .map(t => forwardPass(t, calculations, single = true)) // Recursively compute critical values
                    .++(prevTasksComputed) // Add all the tasks that have already been computed
                    .reduce(_ ++ _) // Merge maps into one map
            }
        }

        // If this is a root task, set value to 0, otherwise find the dependency with the highest earlyFinish value
        val prevEarlyFinish = if(dependencies.isEmpty) 0 else dependencies.values.maxBy(t => t.getEarlyFinish).getEarlyFinish

        //  The early start time is computed with the equation: prev.earlyFinish + this.lag + 1
        //  The early finish time is computed with the equation: this.earlyStart + this.duration - 1
        val earlyStart = prevEarlyFinish + task.getLag + 1
        val earlyFinish = earlyStart + task.getDuration - 1

        val currentTaskCalculations = Map(task -> new CriticalCalculations(earlyStart, earlyFinish, null, null, null))

        // Return only this task's computation if it's an end node, or a dependency needed to be calculated
        if(single || task.getNextTasks.isEmpty) currentTaskCalculations

        else currentTaskCalculations ++ task.getNextTasks.asScala.map(t => forwardPass(t, calculations ++ currentTaskCalculations)).reduce(_ ++ _)
    }

    def backwardPass(calculations: Map[Task, CriticalCalculations], task: Task = null, single: Boolean = false): Map[Task, CriticalCalculations] = {
        // Start backward pass from the task with the largest earlyFinish (i.e. the critical end task)
        if(task == null) return backwardPass(calculations, calculations.maxBy(_._2.getEarlyFinish)._1)

        // Extract current early values
        val currentCalculations = calculations(task)

        val earlyStart = currentCalculations.getEarlyStart
        val earlyFinish = currentCalculations.getEarlyFinish

        // The late finish time is computed with the equation: next.lateStart - next.lag - 1
        val lateFinish = {
            if(task.getNextTasks.isEmpty) calculations.maxBy(_._2.getEarlyFinish)._2.getEarlyFinish // Task doesn't have successors, i.e. this is an end task. Find the highest early finish time
            else  {
                val nextTasksComputed = task.getNextTasks.asScala
                    .filter(t => calculations(t).getLateStart != null)
                    .map(t => Map(t -> calculations(t)))

                val nextTask = task.getNextTasks.asScala
                    .filter(t => calculations(t).getLateStart == null) // Find all non-computed successor tasks
                    .map(t => backwardPass(calculations, t, single = true)) // Recursively compute critical values
                    .++(nextTasksComputed) // Add all the tasks that have already been computed
                    .reduce(_ ++ _) // Merge maps into one map
                    .minBy(_._2.getLateStart) // Return map object

                nextTask._2.getLateStart - nextTask._1.getLag - 1
            }
        }
        // The late start time is computed with the equation: this.lateFinish - this.duration + 1
        val lateStart = lateFinish - task.getDuration + 1
        val float = lateFinish - earlyFinish

        val currentTaskCalculations = Map(task -> new CriticalCalculations(earlyStart, earlyFinish, lateStart, lateFinish, float))

        if(single || task.getPreviousTasks.isEmpty) currentTaskCalculations

        else currentTaskCalculations ++ task.getPreviousTasks.asScala.map(t => backwardPass(calculations ++ currentTaskCalculations, t)).reduce(_ ++ _)
    }

    def forwardBackwardPass(tasks: JSet[Task]): JMap[Task, CriticalCalculations] = {
        // Forward pass must be computed for all starting tasks
        // in case a project has multiple starting tasks (e.g. SampleProjectC)
        val forward = tasks.asScala
            .filter(t => t.getPreviousTasks.isEmpty)
            .map(t => forwardPass(t))
            .reduce(_ ++ _)

        backwardPass(forward).asJava
    }

    def findCriticalPathRecursively(calculations: Map[Task, CriticalCalculations], criticalPath: Set[Task] = null): Set[Task] = {
        // Start critical path by finding starting task
        if(criticalPath == null) {
            val root = calculations
                .find(t => t._2.getEarlyStart == 1 && t._2.getFloat == 0) // Start task has earlyStart of 1 and float 0
                .map(t => t._1).toSet // Get the task wrapped in a Set
            return findCriticalPathRecursively(calculations, root)
        }

        val lastTask = criticalPath
            .maxBy(t => calculations(t).getEarlyFinish) // Find the task that has been added last

        if(lastTask.getNextTasks.isEmpty) Set(lastTask) // Exit recursion when end task is found

        else {
            // Find next critical task
            val nextTask = lastTask
                .getNextTasks.asScala
                .find(t => calculations(t).getFloat == 0)
                .toSet // Wrap task within a Set

            criticalPath ++ findCriticalPathRecursively(calculations, nextTask)
        }
    }

    override def findCriticalPath(tasks: JSet[Task]): JSet[Task] = {
        val calculations = forwardBackwardPass(tasks).asScala.toMap
        findCriticalPathRecursively(calculations).asJava
    }
}
