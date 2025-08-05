package com.school.impl

import com.school.domain.StudentDomain
import com.school.domain.StudentPutRequestDomain
import com.school.port.StudentRepositoryPort
import com.school.usecase.StudentService
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    private val studentRepositoryPort: StudentRepositoryPort
) : StudentService {

    override fun create(student: StudentDomain): StudentDomain {
        return studentRepositoryPort.save(student)
    }

    override fun getById(id: Int): StudentDomain {
        return studentRepositoryPort.getById(id)
    }

    override fun update(id: Int, putRequestModel: StudentPutRequestDomain): StudentDomain {
        return studentRepositoryPort.update(id, putRequestModel)
    }

    override fun delete(id: Int) {
        return studentRepositoryPort.delete(id)
    }
}
