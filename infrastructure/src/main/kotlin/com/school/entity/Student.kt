package com.school.entity

import java.time.LocalDateTime
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "created_at")
    val createdAt: LocalDate = LocalDate.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDate = LocalDate.now(),
)