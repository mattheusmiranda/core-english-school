package com.school.domain

import java.time.LocalDateTime

data class StudentLessonProgressDomain(
    val student: StudentDomain,
    val lesson: LessonDomain,
    val percentageOfProgress: Int,
    val completedAt: LocalDateTime? = null
)
