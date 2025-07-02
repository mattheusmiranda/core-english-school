package entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class StudentCourseId(
    @Column("student_id")
    val studentId: Int = 0,

    @Column("course_id")
    val courseId: Int = 0
) : java.io.Serializable