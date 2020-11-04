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
            a to Calculations(1, 6, 1, 6, 0, true),
            b to Calculations(7, 17, 7, 17, 0, true),
            c to Calculations(7, 13, 8, 14, 1, false),
            d to Calculations(14, 16, 15, 17, 1, false),
            e to Calculations(18, 19, 18, 19, 0, true)
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
            a to Calculations(1, 3, 1, 3, 0, true),
            b to Calculations(4, 7, 4, 7, 0, true),
            c to Calculations(4, 5, 10, 11, 6, false),
            d to Calculations(8, 12, 8, 12, 0, true),
            e to Calculations(6, 6, 12, 12, 6, false),
            f to Calculations(6, 7, 15, 16, 9, false),
            g to Calculations(13, 16, 13, 16, 0, true),
            h to Calculations(17, 19, 17, 19, 0, true)
    )

    return Pair(allTasks, allCalculations)
}

fun getSampleProjectC(): Pair<Array<Task>, HashMap<Task, Calculations>> {
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
    val c = Task("c", 3, arrayListOf(a))
    val d = Task("d", 4, arrayListOf(b))
    val e = Task("e", 3, arrayListOf(b))
    val f = Task("f", 10)
    val g = Task("g", 3, arrayListOf(e, f))
    val h = Task("h", 2, arrayListOf(c, d))

    val allTasks = arrayOf(a, b, c, d, e, f, g, h)

    val allCalculations = hashMapOf(
            a to Calculations(1, 6, 3, 8, 2, false),
            b to Calculations(1, 4, 4, 7, 3, false),
            c to Calculations(7, 9, 9, 11, 2, false),
            d to Calculations(5, 8, 8, 11, 3, false),
            e to Calculations(5, 7, 8, 10, 3, false),
            f to Calculations(1, 10, 1, 10, 0, true),
            g to Calculations(11, 13, 11, 13, 0, true),
            h to Calculations(10, 11, 12, 13, 2, false)
    )

    return Pair(allTasks, allCalculations)
}