package com.school.adapter

import com.school.domain.StudentDomain
import com.school.exception.StudentSaveException
import com.school.exception.UniqueConstraintViolationException
import jakarta.transaction.Transactional
import com.school.jpaRepository.StudentJpaRepository
import com.school.mapper.StudentMapper
import org.springframework.stereotype.Repository
import com.school.port.StudentRepositoryPort
import org.springframework.dao.DataIntegrityViolationException
import java.sql.SQLIntegrityConstraintViolationException

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
        } catch (ex: DataIntegrityViolationException) {
            val sqlEx = ex.rootCause as? SQLIntegrityConstraintViolationException
            val message = sqlEx?.message ?: ""

            val field = when {
                message.contains("students.email") -> "email"
                else -> null
            }

            val errorMessage = if (field != null) {
                "The $field is already in use."
            } else {
                "A unique constraint was violated while saving the student."
            }

            throw UniqueConstraintViolationException(errorMessage)

        } catch (ex: Exception) {
            throw StudentSaveException("Error saving student ID ${student.id}", ex)
        }
    }
}
