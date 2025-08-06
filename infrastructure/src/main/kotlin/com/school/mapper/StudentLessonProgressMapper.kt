package com.school.mapper

import com.school.domain.StudentLessonProgressDomain
import com.school.entity.StudentLessonProgress
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface StudentLessonProgressMapper {

    fun toDomain(entity: StudentLessonProgress): StudentLessonProgressDomain

}