package com.school.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "course_completion_details")
data class CourseCompletionDetails(

    @Id
    @Column(name = "student_lesson_progress_id")
    val studentLessonProgressId: Int,

    @Column(name = "total_classes", nullable = false)
    val totalClasses: Int,

    @Column(name = "completed_classes", nullable = false)
    val completedClasses: Int,

    @Column(name = "remaining_classes", nullable = false)
    val remainingClasses: Int,

    @Column(name = "percentage_completed", nullable = false)
    val percentageCompleted: Double,

    @Column(name = "percentage_remaining", nullable = false)
    val percentageRemaining: Double
)
