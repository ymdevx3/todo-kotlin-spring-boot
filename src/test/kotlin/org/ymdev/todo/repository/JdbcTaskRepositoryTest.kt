package org.ymdev.todo.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@Sql(statements = arrayOf("delete from task"))
class JdbcTaskRepositoryTest {

    @Autowired
    private lateinit var repository: JdbcTaskRepository

    /**
     * 空タスクリストの取得確認
     */
    @Test
    fun confirmToGetEmptyTask() {
        val result = repository.getAllTasks()
        assertThat(result).isEmpty()
    }

    /**
     * 作成タスクの取得確認
     */
    @Test
    fun confirmToGetCreatedTask() {
        val task = repository.createTask("test")
        val result = repository.getTargetTask(task.id)
        assertThat(result).isEqualTo(task)
    }

    /**
     * タスク削除確認
     */
    @Test
    fun confirmToDeleteTask() {
        val task = repository.createTask("test")
        repository.deleteTask(task)
        val result = repository.getTargetTask(task.id)
        assertThat(result).isNull()
    }
}