package org.ymdev.todo.data

/**
 * タスククラス
 * @param id ID
 * @param content 内容
 * @param completed 済/未
 */
data class Task(
        val id: Long,
        var content: String,
        var completed: Boolean
)