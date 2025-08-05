package com.school.domain

import java.time.LocalDateTime

data class LessonCompletedDomain(
    val studentId: Int,
    val lessonId: Int,
    val completedAt: LocalDateTime
)