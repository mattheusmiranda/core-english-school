package com.school.adapter

import com.school.domain.StudentLessonProgressDomain
import com.school.exception.StudentLessonProgressNotFoundException
import com.school.jpaRepository.StudentLessonProgressJpaRepository
import com.school.mapper.StudentLessonProgressMapper
import com.school.usecase.StudentLessonProgressServiceJpa
import org.springframework.stereotype.Repository

@Repository
class StudentLessonProgressServiceJpaAdapter(
    private val repository: StudentLessonProgressJpaRepository
) : StudentLessonProgressServiceJpa {

    override fun findByStudentIdAndLessonId(studentId: Int, lessonId: Int): StudentLessonProgressDomain {
        val entity = repository.findByStudentIdAndLessonId(studentId, lessonId)
            .orElseThrow {
                StudentLessonProgressNotFoundException("Progress for studentId=$studentId and lessonId=$lessonId not found.")
            }

        return StudentLessonProgressMapper.INSTANCE.toDomain(entity)
    }
}
