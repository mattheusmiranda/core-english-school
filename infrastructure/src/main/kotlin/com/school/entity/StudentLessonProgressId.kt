package com.school.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class StudentLessonProgressId(
    @Column(name = "student_id")
    val studentId: Int = 0,

    @Column(name = "lesson_id")
    val lessonId: Int = 0
) : Serializable