package com.school.port

import com.school.domain.StudentDomain
import com.school.model.StudentPutRequestModel

interface StudentRepositoryPort {
    fun save(student: StudentDomain): StudentDomain
    fun getById(id: Int): StudentDomain
    fun update(id: Int, putRequestModel: StudentPutRequestModel): StudentDomain
    fun delete(id: Int)
}