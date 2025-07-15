package com.school.requestModel.student

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class StudentPutRequest(
    @field:NotBlank(message = "The name field must be entered.")
    val name: String,

    @field:Email(message = "In the email field, a value of the email type was not entered.")
    @field:NotBlank(message = "The email field must be filled in.")
    val email: String
)
