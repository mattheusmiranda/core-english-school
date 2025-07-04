package adapter

import domain.StudentDomain
import exception.StudentSaveException
import jakarta.transaction.Transactional
import jpaRepository.StudentJpaRepository
import mapper.StudentMapper
import org.springframework.stereotype.Repository
import port.StudentRepositoryPort

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
