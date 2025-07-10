package com.school.usecase

import com.school.domain.StudentDomain

interface StudentService {
    fun create(student: StudentDomain): StudentDomain
}
