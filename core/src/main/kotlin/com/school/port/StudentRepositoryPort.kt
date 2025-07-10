package com.school.port

import com.school.domain.StudentDomain

interface StudentRepositoryPort {
    fun save(student: StudentDomain): StudentDomain
}