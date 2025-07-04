package port

import domain.StudentDomain

interface StudentRepositoryPort {
    fun save(student: StudentDomain): StudentDomain
}