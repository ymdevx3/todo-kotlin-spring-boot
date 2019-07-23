package org.ymdev.todo.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.ymdev.todo.data.Task

@Repository
class JdbcTaskRepository(private val jdbcTemplate: JdbcTemplate) : ITaskRepository {

    private val rowMapper = RowMapper<Task> { x, _ ->
        Task(x.getLong("id"), x.getString("content"), x.getBoolean("completed"))
    }
    override fun createTask(content: String): Task {
        val insertSql = """insert into task (content) values (?)"""
        jdbcTemplate.update(insertSql, content)

        val query = """select last_insert_id()"""
        val id = jdbcTemplate.queryForObject(query, Long::class.java) ?: throw Exception("ID : not existed")
        return Task(id, content, false)
    }

    override fun getAllTasks(): List<Task> {
        val sql = """select * from task"""
        return jdbcTemplate.query(sql, rowMapper)
    }

    override fun getTargetTask(id: Long): Task? {
        val sql = """select * from task where id = ?"""
        return jdbcTemplate.query(sql, rowMapper, id).firstOrNull()
    }

    override fun updateTask(task: Task) {
        val sql = """update task set content = ?, completed = ? where id = ?"""
        jdbcTemplate.update(sql, task.content, task.completed, task.id)
    }

    override fun deleteTask(task: Task) {
        val sql = """delete from task where id = ?"""
        jdbcTemplate.update(sql, task.id)
    }
}