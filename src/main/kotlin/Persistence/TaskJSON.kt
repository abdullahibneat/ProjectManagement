package Persistence

class TaskJSON() {
    var name: String = ""
    var previousTasks: List<String> = listOf()
    var nextTasks: List<String> = listOf()
    var duration: Int = 0
    var lag: Int = 0

    constructor(name: String, previousTasks: List<String>, nextTasks: List<String>, duration: Int, lag: Int): this() {
        this.name = name
        this.previousTasks = previousTasks
        this.nextTasks = nextTasks
        this.duration = duration
        this.lag = lag
    }
}