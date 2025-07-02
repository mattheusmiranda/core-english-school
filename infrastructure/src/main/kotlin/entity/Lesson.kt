package entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "lessons")
data class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn("course_id")
    val course: Course,

    @Column("title")
    val title: String,

    @Column("duration_seconds")
    val durationSeconds: Int,

    @Column("sequence")
    val sequence: Int,

    @Column( "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
)