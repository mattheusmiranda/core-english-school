package entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class StudentLessonProgressId(
    @Column(name = "student_id")
    val studentId: Int = 0,

    @Column(name = "lesson_id")
    val lessonId: Int = 0
) : java.io.Serializable