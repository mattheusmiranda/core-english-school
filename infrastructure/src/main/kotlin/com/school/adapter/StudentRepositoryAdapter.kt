package com.school.adapter

import com.school.domain.StudentDomain
import com.school.exceptionHandler.StudentDeleteException
import com.school.exceptionHandler.StudentFindException
import com.school.exceptionHandler.StudentPutException
import com.school.exceptionHandler.StudentSaveException
import com.school.exceptionHandler.UniqueConstraintViolationException
import jakarta.transaction.Transactional
import com.school.jpaRepository.StudentJpaRepository
import com.school.mapper.StudentMapper
import com.school.model.StudentPutRequestModel
import org.springframework.stereotype.Repository
import com.school.port.StudentRepositoryPort
import org.springframework.dao.DataIntegrityViolationException
import java.sql.SQLIntegrityConstraintViolationException
import java.time.LocalDate

@Repository
class StudentRepositoryAdapter(
    private val studentJpaRepository: StudentJpaRepository
) : StudentRepositoryPort {

    @Transactional
    override fun save(student: StudentDomain): StudentDomain {
        try {
            val entity = StudentMapper.INSTANCE.toEntity(student)
            val savedEntity = studentJpaRepository.save(entity)
            return StudentMapper.INSTANCE.toModel(savedEntity)
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

        } catch (ex: StudentFindException) {
            throw ex
        } catch (ex: Exception) {
            throw StudentSaveException("Error saving student ID ${student.id}", ex)
        }
    }

    override fun getById(id: Int): StudentDomain {
        try {
            val studentEntity = studentJpaRepository.findById(id)
            val studentDomain = StudentMapper.INSTANCE.toModel(studentEntity.get())
            return studentDomain
        } catch (ex: StudentFindException) {
            throw ex
        } catch (ex: Exception) {
            throw StudentFindException("Error searching for user with ID $id", ex)
        }
    }

    override fun update(id: Int, putRequestModel: StudentPutRequestModel): StudentDomain {
        return try {
            val studentEntity = studentJpaRepository.findById(id)
                .orElseThrow { StudentFindException("Student with ID $id not found") }

            StudentMapper.INSTANCE.updateEntityFromPutRequest(putRequestModel, studentEntity)

            studentEntity.updatedAt = LocalDate.now()

            val saved = studentJpaRepository.save(studentEntity)
            StudentMapper.INSTANCE.toModel(saved)
        } catch (ex: StudentFindException) {
            throw ex
        } catch (ex: Exception) {
            throw StudentPutException("Error updating user with ID $id", ex)
        }
    }

    override fun delete(id: Int) {
        try {
            val studentEntity = studentJpaRepository.findById(id)
                .orElseThrow { StudentFindException("Student with ID $id not found") }

            studentJpaRepository.delete(studentEntity)
        } catch (ex: StudentFindException) {
            throw ex
        } catch (ex: Exception) {
            throw StudentDeleteException("Error deleting student with ID $id", ex)
        }
    }

}
