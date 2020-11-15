import scala.jdk.CollectionConverters.SetHasAsJava
import scala.jdk.CollectionConverters.SetHasAsScala

object CriticalPathScala {
    def main(args: Array[String]): Unit = {
        val a = new Task("a", 6, Set[Task]().asJava)
        val b = new Task("b", 11, Set(a).asJava)
        val c = new Task("c", 7, Set(a).asJava)
        val d = new Task("d", 3, Set(c).asJava)
        val e = new Task("e", 2, Set(b, d).asJava)

        println(forwardPass(a))
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

        //  The early start time is computed with the equation: prev.earlyFinish + 1
        //  The early finish time is computed with the equation: this.earlyStart + this.duration - 1
        //                              This can be simplified: = (prevEarlyFinish + 1) + this.duration - 1 (cancel out the 1s: 1-1=0)
        //                                                      = prevEarlyFinish + this.duration
        val currentTaskCalculations = Map(task -> new CriticalCalculations(prevEarlyFinish + 1, prevEarlyFinish + task.getDuration, null, null, null))

        // Return only this task's computation if it's an end node, or a dependency needed to be calculated
        if(single || task.getNextTasks.isEmpty) currentTaskCalculations

        else currentTaskCalculations ++ task.getNextTasks.asScala.map(t => forwardPass(t, calculations ++ currentTaskCalculations)).reduce(_ ++ _)
    }
}
