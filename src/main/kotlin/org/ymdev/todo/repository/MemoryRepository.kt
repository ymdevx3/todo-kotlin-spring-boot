package org.ymdev.todo.repository

import org.springframework.stereotype.Repository
import org.ymdev.todo.data.Task

@Repository
class MemoryRepository : ITaskRepository {

    private val tasks: MutableList<Task> = mutableListOf()

    /**
     * タスク作成
     * @param content 内容
     * @return タスク
     */
    override fun createTask(content: String): Task {
        val maxId = this.tasks.map{x -> x.id}.max() ?: 0
        val id = maxId + 1
        val task = Task(id, content, false)
        this.tasks.add(task)

        return task
    }

    /**
     * タスク取得
     * @return タスク全件
     */
    override fun getAllTasks(): List<Task> {
        return this.tasks.toList()
    }

    /**
     * ID指定でタスク取得
     * @param id ID
     * @return 対象タスク
     */
    override fun getTargetTask(id: Long): Task? {
        return this.tasks.find {x -> x.id == id}
    }

    /**
     * タスク更新
     * @param task タスク
     */
    override fun updateTask(task: Task) {
        this.tasks.replaceAll { x ->
            if (x.id == task.id) task
            else x
        }
    }

    /**
     * タスク削除
     * @param task タスク
     */
    override fun deleteTask(task: Task) {
        this.tasks.remove(task)
    }
}