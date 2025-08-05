package com.school.usecase

import com.school.domain.StudentDomain
import com.school.domain.StudentPutRequestDomain

interface StudentService {
    fun create(student: StudentDomain): StudentDomain
    fun getById(id: Int): StudentDomain
    fun update(id: Int, putRequestModel: StudentPutRequestDomain): StudentDomain
    fun delete(id: Int)
}
