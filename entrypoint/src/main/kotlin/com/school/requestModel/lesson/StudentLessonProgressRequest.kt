package com.school.requestModel.lesson

import java.time.LocalDateTime

data class StudentLessonProgressRequest(
    val studentId: Int,
    val lessonId: Int,
    val percentageOfProgress: Int,
    val completedAt: LocalDateTime? = null
)
