package com.school.impl

import com.school.domain.LessonDomain
import com.school.port.LessonRepositoryPort
import com.school.usecase.LessonService
import org.springframework.stereotype.Service

@Service
class LessonServiceImpl(
    private val lessonRepositoryPort: LessonRepositoryPort
): LessonService {
    override fun getById(id: Int): LessonDomain {
        return lessonRepositoryPort.getById(id)
    }
}