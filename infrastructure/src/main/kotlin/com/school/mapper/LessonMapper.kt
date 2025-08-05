package com.school.mapper

import com.school.domain.LessonDomain
import com.school.entity.Lesson
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface LessonMapper {
    companion object {
        val INSTANCE: LessonMapper = Mappers.getMapper(LessonMapper::class.java)
    }

    fun toDomain(entity: Lesson): LessonDomain

}