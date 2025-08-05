package com.school.mapper.lesson

import com.school.domain.StudentLessonProgressDomain
import com.school.requestModel.lesson.StudentLessonProgressRequest
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface StudentLessonProgressEntryPointMapper {

    companion object {
        val INSTANCE: StudentLessonProgressEntryPointMapper = Mappers.getMapper(StudentLessonProgressEntryPointMapper::class.java)
    }

    fun toDomainByRequest(classProgressRequest: StudentLessonProgressRequest): StudentLessonProgressDomain
}