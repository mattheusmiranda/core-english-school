package com.school.domain

import java.time.LocalDate

data class StudentDomain(
    val id: Int = 0,
    val name: String,
    val email: String,
    val createdAt: LocalDate = LocalDate.now(),
    val updatedAt: LocalDate = LocalDate.now()
)