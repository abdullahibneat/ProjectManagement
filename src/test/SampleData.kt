/**
 * PROJETCS
 *
 * Diagrams provided in the following format:
 *    +---------------------------+
 *    |  earlyStart   earlyFinish |
 *    |         TASK NAME         |
 *    |   lateStart    lateFinish |
 *    +---------------------------+
 */

fun getSampleProjectA(): Triple<Set<Task>, HashMap<Task, CriticalCalculations>, Set<Task>> {
    /**
        +-------+     +-------+                   +-------+
        | 1    6|     | 7   17|                   |18   19|
        |   A   +---->+   B   +----------------+->+   E   |
        | 1    6|  |  | 7   17|                ^  |18   19|
        +-------+  |  +-------+                |  +-------+
                   |  +-------+     +-------+  |
                   |  | 7   13|     |14   16|  |
                   +->+   C   +---->+   D   +--+
                      | 8   14|     |15   17|
                      +-------+     +-------+
    */

    val a = Task("a", 6)
    val b = Task("b", 11, mutableSetOf(a))
    val c = Task("c", 7, mutableSetOf(a))
    val d = Task("d", 3, mutableSetOf(c))
    val e = Task("e", 2, mutableSetOf(b, d))

    val allTasks = setOf(a, b, c, d, e)

    val allCalculations = hashMapOf(
            a to CriticalCalculations(1, 6, 1, 6, 0),
            b to CriticalCalculations(7, 17, 7, 17, 0),
            c to CriticalCalculations(7, 13, 8, 14, 1),
            d to CriticalCalculations(14, 16, 15, 17, 1),
            e to CriticalCalculations(18, 19, 18, 19, 0)
    )

    val criticalPath = setOf(a,b,e)

    return Triple(allTasks, allCalculations, criticalPath)
}



fun getSampleProjectB(): Triple<Set<Task>, HashMap<Task, CriticalCalculations>, Set<Task>> {
    /**
        +-------+     +-------+     +-------+     +-------+     +-------+
        | 1    3|     | 4    7|     | 8   12|     |13   16|     |17   19|
        |   A   +--+->+   B   +---->+   D   +--+->+   G   +--+->+   H   |
        | 1    3|  |  | 4    7|     | 8   12|  ^  |13   16|  ^  |17   19|
        +-------+  |  +-------+     +-------+  |  +-------+  |  +-------+
                   |                           |             |
                   |  +-------+     +-------+  |             |
                   |  | 4    5|     | 6    6|  |             |
                   +->+   C   +-+-->+   E   +--+             |
                      |10   11| |   |12   12|                |
                      +-------+ |   +-------+                |
                                |                            |
                                |   +-------+                |
                                |   | 6    7|                |
                                +-->+   F   +----------------+
                                    |15   16|
                                    +-------+
    */

    val a = Task("a", 3)
    val b = Task("b", 4, mutableSetOf(a))
    val c = Task("c", 2, mutableSetOf(a))
    val d = Task("d", 5, mutableSetOf(b))
    val e = Task("e", 1, mutableSetOf(c))
    val f = Task("f", 2, mutableSetOf(c))
    val g = Task("g", 4, mutableSetOf(d, e))
    val h = Task("h", 3, mutableSetOf(f, g))

    val allTasks = setOf(a, b, c, d, e, f, g, h)

    val allCalculations = hashMapOf(
            a to CriticalCalculations(1, 3, 1, 3, 0),
            b to CriticalCalculations(4, 7, 4, 7, 0),
            c to CriticalCalculations(4, 5, 10, 11, 6),
            d to CriticalCalculations(8, 12, 8, 12, 0),
            e to CriticalCalculations(6, 6, 12, 12, 6),
            f to CriticalCalculations(6, 7, 15, 16, 9),
            g to CriticalCalculations(13, 16, 13, 16, 0),
            h to CriticalCalculations(17, 19, 17, 19, 0)
    )

    val criticalPath = setOf(a,b,d,g,h)

    return Triple(allTasks, allCalculations, criticalPath)
}

fun getSampleProjectC(): Triple<Set<Task>, HashMap<Task, CriticalCalculations>, Set<Task>> {
    /**
         +-------+     +-------+     +-------+
         | 1    6|     | 7    9|     |10   11|
         |   A   +---->+   C   +--+->+   H   |
         | 3    8|     | 9   11|  ^  |12   13|
         +-------+     +-------+  |  +-------+
                                  |
         +-------+     +-------+  |
         | 1    4|     | 5    8|  |
         |   B   +--+->+   D   +--+
         | 4    7|  |  | 8   11|
         +-------+  |  +-------+
                    |
                    |  +-------+     +-------+
                    |  | 5    7|     |11   13|
                    +->+   E   +--+->+   G   |
                       | 8   10|  ^  |11   13|
                       +-------+  |  +-------+
                                  |
         +-------+                |
         | 1   10|                |
         |   F   +----------------+
         | 1   10|
         +-------+
     */

    val a = Task("a", 6)
    val b = Task("b", 4)
    val c = Task("c", 3, mutableSetOf(a))
    val d = Task("d", 4, mutableSetOf(b))
    val e = Task("e", 3, mutableSetOf(b))
    val f = Task("f", 10)
    val g = Task("g", 3, mutableSetOf(e, f))
    val h = Task("h", 2, mutableSetOf(c, d))

    val allTasks = setOf(a, b, c, d, e, f, g, h)

    val allCalculations = hashMapOf(
            a to CriticalCalculations(1, 6, 3, 8, 2),
            b to CriticalCalculations(1, 4, 4, 7, 3),
            c to CriticalCalculations(7, 9, 9, 11, 2),
            d to CriticalCalculations(5, 8, 8, 11, 3),
            e to CriticalCalculations(5, 7, 8, 10, 3),
            f to CriticalCalculations(1, 10, 1, 10, 0),
            g to CriticalCalculations(11, 13, 11, 13, 0),
            h to CriticalCalculations(10, 11, 12, 13, 2)
    )

    val criticalPath = setOf(f,g)

    return Triple(allTasks, allCalculations, criticalPath)
}