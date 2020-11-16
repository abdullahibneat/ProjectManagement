/**
 * PROJETCS
 *
 * Diagrams provided in the following format:
 *    +---------------------------+
 *    |  earlyStart   earlyFinish |    lAG
 *    |         TASK NAME         |------------
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

fun getSampleProjectD(): Triple<Set<Task>, HashMap<Task, CriticalCalculations>, Set<Task>> {
    /**

    +--=---+      +------+        +------+      +------+
    | 1   5|      | 6  17|        |18  19|      |23  25|
    |  A   +------+  B   +-------+  E    +---+--+  F   |
    | 1   5|      | 9  20|        |21  22|   |  |23  25|
    +--+--+      +-------+        +------+   |  +------+
       |                                     |
       |                                     |
       |                                     |
       |                                     |
       |  +------+      +------+             |
       |  |6  11 |  4   |16  22|             |
       +--+ C    +------+  D   +-------------+
          |6  11 |      |16  22|
          +------+      +------+



     */
    val a = Task("a", 5)
    val b = Task("b", 12, mutableSetOf(a))
    val c = Task("c", 6, mutableSetOf(a))
    val d = Task("d", 7, mutableSetOf(c), 4)
    val e = Task("e", 2, mutableSetOf(b))
    val f = Task("f", 3, mutableSetOf(e,d))

    val allTasks = setOf(a, b, c, d, e, f)

    val allCalculations = hashMapOf(
            a to CriticalCalculations(1, 5, 1, 5, 0),
            b to CriticalCalculations(6, 17, 9, 20, 3),
            c to CriticalCalculations(6, 11, 6, 11, 0),
            d to CriticalCalculations(16, 22, 16, 22, 0),
            e to CriticalCalculations(18, 19, 21, 22, 3),
            f to CriticalCalculations(23, 25, 23, 25, 0)
    )

    val criticalPath = setOf(a,c,d,f)

    return Triple(allTasks, allCalculations, criticalPath)
}

fun getSampleProjectE(): Triple<Set<Task>, HashMap<Task, CriticalCalculations>, Set<Task>> {
    /**
    +-------+     +-------+     +-------+     +-------+
    |1     2|     |3     6|  5  |12   22|     |34   35|
    |   A   +-----+   B   +-----+   C   +--+--+   J   |
    |1    13|     |14   17|     |23   33|  |  |34   35|
    +-------+     +-------+     +-------+  |  +-------+
                                           |
    +-------+     +-------+     +-------+  |
    |1     7|  4  |12   24|     |25   33|  |
    |   D   +--+--+   E   +-----+   F   +--+
    |1     7|  |  |12   24|     |25   33|  |
    +-------+  |  +-------+     +-------+  |
               |                           |
               |                           |
               |  +-------+     +-------+  |
               |  |1     2|  3  |13   17|  |
               +--+   G   +--+--+   H   +--+
                  |1    13|  |  |29   33|  |
                  +-------+  |  +-------+  |
                             |             |
                             |             |
                             |  +-------+  |
                             |  |10   15|  |
                             +--+   I   +--+
                                |28   33|
                                +-------+


     */
    val a = Task("a", 2)
    val b = Task("b", 4, mutableSetOf(a))
    val c = Task("c", 11, mutableSetOf(b),5)
    val d = Task("d", 7)
    val e = Task("e", 13, mutableSetOf(d),4)
    val f = Task("f", 9, mutableSetOf(e))
    val g = Task("g", 2, mutableSetOf(d))
    val h = Task("h", 5, mutableSetOf(g),3)
    val i = Task("i", 6, mutableSetOf(g))
    val j = Task("j", 2, mutableSetOf(c,f,h,i))

    val allTasks = setOf(a, b, c, d, e, f, g , h , i, j)

    val allCalculations = hashMapOf(
            a to CriticalCalculations(1, 2, 12, 13, 11),
            b to CriticalCalculations(3, 6, 14, 17, 11),
            c to CriticalCalculations(12, 22, 23, 33, 11),
            d to CriticalCalculations(1, 7, 1, 7, 0),
            e to CriticalCalculations(12, 24, 12, 24, 0),
            f to CriticalCalculations(25, 33, 25, 33, 0),
            g to CriticalCalculations(8, 9, 26, 27, 18),
            h to CriticalCalculations(13, 17, 29, 33, 16),
            i to CriticalCalculations(10, 15, 28, 33, 18),
            j to CriticalCalculations(34, 35, 34, 35, 0)
    )

    val criticalPath = setOf(d,e,f,j)

    return Triple(allTasks, allCalculations, criticalPath)
}