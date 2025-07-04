package domain

import java.time.LocalDateTime

data class StudentDomain(
    val id: Int = 0,
    val name: String,
    val email: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)