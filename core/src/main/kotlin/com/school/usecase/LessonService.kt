package com.school.usecase

import com.school.domain.LessonDomain

interface LessonService {
    fun getById(id: Int): LessonDomain
}