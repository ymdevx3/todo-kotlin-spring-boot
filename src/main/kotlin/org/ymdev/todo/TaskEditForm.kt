package org.ymdev.todo

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * タスク更新
 * @param content 内容
 * @param completed 済/未
 */
data class TaskEditForm (
        @field:NotBlank
        @field:Size(max = 30)
        var content: String? = null,
        var completed: Boolean = false
)