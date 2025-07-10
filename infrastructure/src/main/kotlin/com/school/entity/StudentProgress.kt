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
@Table(name = "student_progress")
data class StudentProgress(
    @EmbeddedId
    val id: StudentProgressId = StudentProgressId(),

    @ManyToOne
    @MapsId(value = "studentId")
    @JoinColumn(name = "student_id")
    val student: Student,

    @ManyToOne
    @MapsId(value = "courseId")
    @JoinColumn(name = "course_id")
    val course: Course,

    @Column(name = "progress_percent")
    val progressPercent: Double = 0.0,

    @Column(name = "last_updated")
    val lastUpdated: LocalDateTime = LocalDateTime.now()
)