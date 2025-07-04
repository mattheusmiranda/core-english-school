package usecase

import domain.StudentDomain
import port.StudentRepositoryPort

class StudentService(
    private val saveStudentRepositoryPort: StudentRepositoryPort
) {
    fun create(student: StudentDomain): StudentDomain {
        return saveStudentRepositoryPort.save(student)
    }
}