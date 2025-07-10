package com.school.adapter

import com.school.domain.StudentDomain
import com.school.exception.StudentSaveException
import jakarta.transaction.Transactional
import com.school.jpaRepository.StudentJpaRepository
import com.school.mapper.StudentMapper
import org.springframework.stereotype.Repository
import com.school.port.StudentRepositoryPort

@Repository
class StudentRepositoryAdapter(
    private val studentJpaRepository: StudentJpaRepository
) : StudentRepositoryPort {

    @Transactional
    override fun save(student: StudentDomain): StudentDomain {
        return try {
            val entity = StudentMapper.INSTANCE.toEntity(student)
            val savedEntity = studentJpaRepository.save(entity)
            StudentMapper.INSTANCE.toModel(savedEntity)
        } catch (ex: Exception) {
            throw StudentSaveException("Error saving student with ID ${student.id}", ex)
        }
    }
}
