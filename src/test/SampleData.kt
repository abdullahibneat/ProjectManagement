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

fun getSampleProjectA(): Pair<Array<Task>, HashMap<Task, Calculations>> {
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
    val b = Task("b", 11, arrayListOf(a))
    val c = Task("c", 7, arrayListOf(a))
    val d = Task("d", 3, arrayListOf(c))
    val e = Task("e", 2, arrayListOf(b, d))

    val allTasks = arrayOf(a, b, c, d, e)

    val allCalculations = hashMapOf(
            a to Calculations(1, 6, 1, 6),
            b to Calculations(7, 17, 7, 17),
            c to Calculations(7, 13, 8, 14),
            d to Calculations(14, 16, 15, 17),
            e to Calculations(18, 19, 18, 19)
    )

    return Pair(allTasks, allCalculations)
}

fun getSampleProjectB(): Pair<Array<Task>, HashMap<Task, Calculations>> {
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
    val b = Task("b", 4, arrayListOf(a))
    val c = Task("c", 2, arrayListOf(a))
    val d = Task("d", 5, arrayListOf(b))
    val e = Task("e", 1, arrayListOf(c))
    val f = Task("f", 2, arrayListOf(c))
    val g = Task("g", 4, arrayListOf(d, e))
    val h = Task("h", 3, arrayListOf(f, g))

    val allTasks = arrayOf(a, b, c, d, e, f, g, h)

    val allCalculations = hashMapOf(
            a to Calculations(1, 3, 1, 3),
            b to Calculations(4, 7, 4, 7),
            c to Calculations(4, 5, 10, 11),
            d to Calculations(8, 12, 8, 12),
            e to Calculations(6, 6, 12, 12),
            f to Calculations(6, 7, 15, 16),
            g to Calculations(13, 16, 13, 16),
            h to Calculations(17, 19, 17, 19)
    )

    return Pair(allTasks, allCalculations)
}