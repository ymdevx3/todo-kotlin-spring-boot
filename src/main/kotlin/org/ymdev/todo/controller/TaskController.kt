package org.ymdev.todo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.ymdev.todo.NewTaskForm
import org.ymdev.todo.TaskEditForm
import org.ymdev.todo.repository.JdbcTaskRepository
import javax.validation.Valid

@Controller
@RequestMapping("todo")
class TaskController(private val taskRepository: JdbcTaskRepository) {

    @GetMapping("")
    fun index(model: Model): String {
        val tasks = taskRepository.getAllTasks()
        model.addAttribute("tasks", tasks)
        return "todo/index"
    }

    @GetMapping("new")
    fun new(@ModelAttribute("NewTaskForm") form: NewTaskForm): String {
        return "todo/new"
    }

    @PostMapping("")
    fun create(@Valid @ModelAttribute("NewTaskForm") form: NewTaskForm,
               bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "todo/new"
        }

        val content = requireNotNull(form.content)
        taskRepository.createTask(content)
        return "redirect:/todo"
    }

    @GetMapping(value = "{id}/edit")
    fun edit(@PathVariable("id") id: Long,
             @ModelAttribute("TaskEditForm") form: TaskEditForm): String {
        val task = taskRepository.getTargetTask(id) ?: throw Exception("Task is not found !")

        form.content = task.content
        form.completed = task.completed

        return "todo/edit"
    }

    @PatchMapping("{id}", params = arrayOf("update"))
    fun update(@PathVariable("id") id: Long,
               @Validated @ModelAttribute("TaskEditForm") form: TaskEditForm,
               bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "todo/edit"
        }

        val task = taskRepository.getTargetTask(id) ?: throw Exception("Task is not found !")
        val newTask = task.copy(content = requireNotNull(form.content), completed = form.completed)
        taskRepository.updateTask(newTask)
        return "redirect:/todo"
    }

    @PatchMapping("{id}", params = arrayOf("delete"))
    fun delete(@PathVariable("id") id: Long,
               @Validated @ModelAttribute("TaskEditForm") form: TaskEditForm,
               bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "todo/edit"
        }

        val task = taskRepository.getTargetTask(id) ?: throw Exception("Task is not found !")
        taskRepository.deleteTask(task)
        return "redirect:/todo"
    }
}