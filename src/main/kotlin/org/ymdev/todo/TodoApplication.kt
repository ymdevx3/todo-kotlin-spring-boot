package org.ymdev.todo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class TodoApplication {

	@Bean
	fun commandLineRunner(jdbcTemplate: JdbcTemplate) = CommandLineRunner {

		val sql = """create table if not exists task (
     		id bigint primary key auto_increment,
     		content varchar(255) not null,
     		completed boolean not null default false
    	 )"""

		jdbcTemplate.execute(sql.trimMargin())
	}
}

fun main(args: Array<String>) {
	runApplication<TodoApplication>(*args)
}
