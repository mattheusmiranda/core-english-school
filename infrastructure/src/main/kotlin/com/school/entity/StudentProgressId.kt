package com.school.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class StudentProgressId(
    @Column(name = "student_id")
    val studentId: Int = 0,

    @Column(name = "course_id")
    val courseId: Int = 0
) : Serializable