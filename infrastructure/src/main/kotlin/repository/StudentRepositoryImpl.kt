package repository

import entity.Student
import org.springframework.stereotype.Repository
import port.StudentRepositoryPort

@Repository
class StudentRepositoryImpl : StudentRepositoryPort {
    override fun save(student: Student): Student {
        TODO("Not yet implemented")
    }
}