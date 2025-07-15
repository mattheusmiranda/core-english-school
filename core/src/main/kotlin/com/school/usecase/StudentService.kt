package com.school.usecase

import com.school.domain.StudentDomain
import com.school.model.StudentPutRequestModel

interface StudentService {
    fun create(student: StudentDomain): StudentDomain
    fun getById(id: Int): StudentDomain
    fun update(id: Int, putRequestModel: StudentPutRequestModel): StudentDomain
    fun delete(id: Int)
}
