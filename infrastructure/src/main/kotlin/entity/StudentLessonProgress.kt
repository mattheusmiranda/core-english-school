package entity

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

    @ManyToOne
    @MapsId(value = "studentId")
    @JoinColumn(name = "student_id")
    val student: Student,

    @ManyToOne
    @MapsId(value = "lessonId")
    @JoinColumn(name = "lesson_id")
    val lesson: Lesson,

    @Column(name = "completed_at")
    val completedAt: LocalDateTime? = null
)