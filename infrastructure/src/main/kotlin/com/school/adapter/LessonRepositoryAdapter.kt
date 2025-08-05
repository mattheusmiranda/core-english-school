package com.school.adapter

import com.school.domain.LessonDomain
import com.school.exception.lesson.LessonFindException
import com.school.jpaRepository.LessonJpaRepository
import com.school.mapper.LessonMapper
import com.school.port.LessonRepositoryPort
import org.springframework.stereotype.Repository

@Repository
class LessonRepositoryAdapter(
    private val lessonJpaRepository: LessonJpaRepository
): LessonRepositoryPort {
    override fun getById(id: Int): LessonDomain {
        try {
            val lessonEntity = lessonJpaRepository.findById(id)
            val lessonDomain = LessonMapper.INSTANCE.toDomain(lessonEntity.get())
            return lessonDomain
        } catch (ex: Exception) {
            throw LessonFindException("Error searching for lesson with ID $id\"", ex)
        }
    }
}