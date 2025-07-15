package com.school.responseModel.student

import java.time.LocalDate

data class StudentResponse(
    val id: Int = 0,
    val name: String,
    val email: String,
    val createdAt: LocalDate = LocalDate.now(),
    val updatedAt: LocalDate = LocalDate.now()
)