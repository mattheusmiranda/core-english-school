package com.school.domain

import java.time.LocalDateTime

data class StudentProgressDomain(
    val studentId: Int,
    val percentageOfCourseCompleted: Int,
    val badges: List<String>,
    val updatedAt: LocalDateTime
)