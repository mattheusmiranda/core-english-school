package com.school.adapter

import com.school.domain.StudentDomain
import com.school.exceptionHandler.StudentDeleteException
import com.school.exceptionHandler.StudentFindException
import com.school.exceptionHandler.StudentPutException
import com.school.exceptionHandler.StudentSaveException
import com.school.exceptionHandler.UniqueConstraintViolationException
import com.school.jpaRepository.StudentJpaRepository
import com.school.mapper.StudentMapper
import com.school.model.StudentPutRequestModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.dao.DataIntegrityViolationException
import java.sql.SQLIntegrityConstraintViolationException
import java.time.LocalDate
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class StudentRepositoryAdapterTest {

    @Mock
    private lateinit var studentJpaRepository: StudentJpaRepository

    @InjectMocks
    private lateinit var studentRepositoryAdapter: StudentRepositoryAdapter

    @Test
    fun `should save student successfully`() {
        val domain = StudentDomain(1, "John Doe", "john@example.com", LocalDate.now(), LocalDate.now())
        val entity = StudentMapper.Companion.INSTANCE.toEntity(domain)
        BDDMockito.given(studentJpaRepository.save(ArgumentMatchers.any())).willReturn(entity)

        val result = studentRepositoryAdapter.save(domain)

        Assertions.assertEquals(domain.id, result.id)
        Assertions.assertEquals(domain.name, result.name)
        Assertions.assertEquals(domain.email, result.email)
    }

    @Test
    fun `should throw UniqueConstraintViolationException when duplicate email`() {
        val domain = StudentDomain(1, "John Doe", "john@example.com", LocalDate.now(), LocalDate.now())
        val sqlEx = SQLIntegrityConstraintViolationException("Duplicate entry for key 'students.email'")
        val dataIntegrityViolationException = DataIntegrityViolationException("duplicate", sqlEx)

        BDDMockito.given(studentJpaRepository.save(ArgumentMatchers.any())).willThrow(dataIntegrityViolationException)

        val ex = assertThrows<UniqueConstraintViolationException> {
            studentRepositoryAdapter.save(domain)
        }

        Assertions.assertEquals("The email is already in use.", ex.message)
    }

    @Test
    fun `should throw generic UniqueConstraintViolationException when constraint unknown`() {
        val domain = StudentDomain(1, "John Doe", "john@example.com", LocalDate.now(), LocalDate.now())
        val sqlEx = SQLIntegrityConstraintViolationException("Some other constraint")
        val dataIntegrityViolationException = DataIntegrityViolationException("duplicate", sqlEx)

        BDDMockito.given(studentJpaRepository.save(ArgumentMatchers.any())).willThrow(dataIntegrityViolationException)

        val ex = assertThrows<UniqueConstraintViolationException> {
            studentRepositoryAdapter.save(domain)
        }

        Assertions.assertEquals("A unique constraint was violated while saving the student.", ex.message)
    }

    @Test
    fun `should throw StudentSaveException on generic exception during save`() {
        val domain = StudentDomain(1, "John Doe", "john@example.com", LocalDate.now(), LocalDate.now())

        BDDMockito.given(studentJpaRepository.save(ArgumentMatchers.any())).willThrow(RuntimeException("DB down"))

        val ex = assertThrows<StudentSaveException> {
            studentRepositoryAdapter.save(domain)
        }

        Assertions.assertTrue(ex.message!!.contains("Error saving student ID"))
    }

    @Test
    fun `should get student by id successfully`() {
        val student = StudentDomain(
            1, "Matheus", "matheus@gmail.com",
            LocalDate.now(), LocalDate.now()
        )
        val entity = StudentMapper.Companion.INSTANCE.toEntity(student)

        BDDMockito.given(studentJpaRepository.findById(1)).willReturn(Optional.of(entity))

        val result = studentRepositoryAdapter.getById(1)

        Assertions.assertEquals(1, result.id)
    }

    @Test
    fun `should throw StudentFindException on exception during getById`() {
        BDDMockito.given(studentJpaRepository.findById(1)).willThrow(RuntimeException("DB error"))

        val ex = assertThrows<StudentFindException> {
            studentRepositoryAdapter.getById(1)
        }

        Assertions.assertTrue(ex.message!!.contains("Error searching for user"))
    }

    @Test
    fun `should update student successfully`() {
        val student = StudentDomain(
            1, "Matheus", "matheus@gmail.com",
            LocalDate.now(), LocalDate.now()
        )
        val entity = StudentMapper.Companion.INSTANCE.toEntity(student)
        val putModel = StudentPutRequestModel("Jane Doe", "jane@example.com")
        BDDMockito.given(studentJpaRepository.findById(1)).willReturn(Optional.of(entity))
        BDDMockito.given(studentJpaRepository.save(ArgumentMatchers.any())).willReturn(entity)

        val result = studentRepositoryAdapter.update(1, putModel)

        Assertions.assertNotNull(result)
    }

    @Test
    fun `should throw StudentFindException when student not found during update`() {
        val putModel = StudentPutRequestModel("Matheus_1", "matheus1@gmail.com")
        BDDMockito.given(studentJpaRepository.findById(1)).willReturn(Optional.empty())

        val ex = assertThrows<StudentFindException> {
            studentRepositoryAdapter.update(1, putModel)
        }

        Assertions.assertEquals("Student with ID 1 not found", ex.message)
    }

    @Test
    fun `should throw StudentPutException on generic exception during update`() {
        val student = StudentDomain(
            1, "Matheus", "matheus@gmail.com",
            LocalDate.now(), LocalDate.now()
        )
        val entity = StudentMapper.Companion.INSTANCE.toEntity(student)
        val putModel = StudentPutRequestModel("Matheus_1", "matheus1@gmail.com")

        BDDMockito.given(studentJpaRepository.findById(1)).willReturn(Optional.of(entity))
        BDDMockito.given(studentJpaRepository.save(ArgumentMatchers.any())).willThrow(RuntimeException("DB down"))

        val ex = assertThrows<StudentPutException> {
            studentRepositoryAdapter.update(1, putModel)
        }

        Assertions.assertTrue(ex.message!!.contains("Error updating user with ID"))
    }

    @Test
    fun `should delete student successfully`() {
        val student = StudentDomain(
            1, "Matheus", "matheus@gmail.com",
            LocalDate.now(), LocalDate.now()
        )
        val entity = StudentMapper.Companion.INSTANCE.toEntity(student)

        BDDMockito.given(studentJpaRepository.findById(1)).willReturn(Optional.of(entity))

        studentRepositoryAdapter.delete(1)

        BDDMockito.then(studentJpaRepository).should().delete(entity)
    }

    @Test
    fun `should throw StudentFindException when student not found during delete`() {
        BDDMockito.given(studentJpaRepository.findById(1)).willReturn(Optional.empty())

        val ex = assertThrows<StudentFindException> {
            studentRepositoryAdapter.delete(1)
        }

        Assertions.assertEquals("Student with ID 1 not found", ex.message)
    }

    @Test
    fun `should throw StudentDeleteException on generic exception during delete`() {
        val student = StudentDomain(
            1, "Matheus", "matheus@gmail.com",
            LocalDate.now(), LocalDate.now()
        )
        val entity = StudentMapper.Companion.INSTANCE.toEntity(student)

        BDDMockito.given(studentJpaRepository.findById(1))
            .willReturn(Optional.of(entity))
        BDDMockito.willThrow(RuntimeException("DB error")).given(studentJpaRepository)
            .delete(ArgumentMatchers.any())

        val ex = assertThrows<StudentDeleteException> {
            studentRepositoryAdapter.delete(1)
        }

        Assertions.assertTrue(ex.message!!.contains("Error deleting student with ID"))
    }

}