package com.school.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "student_lessons_progress")
data class StudentLessonProgress(
    @EmbeddedId
    val id: StudentLessonProgressId = StudentLessonProgressId(),

    @JoinColumn(name = "student_id")
    val studentId: Int,

    @Column(name = "lesson_id")
    val lessonId: Int,

    @Column(name = "percentage_of_progress")
    val percentageOfProgress: Int,

    @Column(name = "completed_at")
    val completedAt: LocalDateTime? = null
)