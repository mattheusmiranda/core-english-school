package com.school.port

import com.school.domain.StudentDomain
import com.school.domain.StudentPutRequestDomain

interface StudentRepositoryPort {
    fun save(student: StudentDomain): StudentDomain
    fun getById(id: Int): StudentDomain
    fun update(id: Int, putRequestModel: StudentPutRequestDomain): StudentDomain
    fun delete(id: Int)
}