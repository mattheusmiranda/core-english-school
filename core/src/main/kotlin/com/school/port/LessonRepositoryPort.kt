package com.school.port

import com.school.domain.LessonDomain

interface LessonRepositoryPort {
    fun getById(id: Int): LessonDomain
}