package org.ymdev.todo

import org.springframework.stereotype.Component
import org.ymdev.todo.data.Task

@Component
class TaskStream {
    fun activeCount(tasks: List<Task>): Int {
        return tasks.filter {x -> !x.completed}.count()
    }
}