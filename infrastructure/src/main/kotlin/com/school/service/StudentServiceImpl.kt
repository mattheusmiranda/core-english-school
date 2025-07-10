package com.school.service

import com.school.domain.StudentDomain
import com.school.port.StudentRepositoryPort
import com.school.usecase.StudentService
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    private val saveStudentRepositoryPort: StudentRepositoryPort
) : StudentService {

    override fun create(student: StudentDomain): StudentDomain {
        return saveStudentRepositoryPort.save(student)
    }
}
