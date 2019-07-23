package org.ymdev.todo

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * 新規タスク
 * @param content 内容
 */
data class NewTaskForm(
        @field:NotBlank
        @field:Size(max = 30)
        var content: String? = null
)