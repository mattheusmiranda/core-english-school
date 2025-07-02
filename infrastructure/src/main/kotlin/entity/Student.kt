package entity

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column("name")
    val name: String,

    @Column("email")
    val email: String,

    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()

    /*@OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    val studentCourses: List<StudentCourse> = emptyList(),*/
)