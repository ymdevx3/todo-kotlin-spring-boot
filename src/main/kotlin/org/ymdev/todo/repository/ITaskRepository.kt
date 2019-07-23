package org.ymdev.todo.repository

import org.ymdev.todo.data.Task

interface ITaskRepository {

    fun createTask(content: String): Task

    fun getAllTasks(): List<Task>

    fun getTargetTask(id: Long): Task?

    fun updateTask(task: Task)

    fun deleteTask(task: Task)
}