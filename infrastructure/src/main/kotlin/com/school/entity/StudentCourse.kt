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
@Table(name = "student_courses")
data class StudentCourse(
    @EmbeddedId
    val id: StudentCourseId = StudentCourseId(),

    @ManyToOne
    @MapsId(value = "studentId")
    @JoinColumn(name = "student_id")
    val student: Student,

    @ManyToOne
    @MapsId(value = "courseId")
    @JoinColumn(name = "course_id")
    val course: Course,

    @Column(name = "enrolled_at")
    val enrolledAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "status")
    val status: String? = null
)