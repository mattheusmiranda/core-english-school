package responseModel

import java.time.LocalDateTime

data class StudentResponse(
    val id: Int = 0,
    val name: String,
    val email: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)