package entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column("title")
    val title: String,

    @Column("description")
    val description: String? = null,

    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
)