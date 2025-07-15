package com.school.requestModel.student

import jakarta.validation.constraints.NotBlank

data class GetStudentByNameRequest(
    @field:NotBlank(message = "The id field must be entered.")
    val id: Int,
)
