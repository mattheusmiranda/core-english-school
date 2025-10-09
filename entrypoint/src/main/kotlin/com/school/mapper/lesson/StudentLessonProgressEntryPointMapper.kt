package com.school.mapper.lesson

import com.school.domain.StudentLessonProgressDomain
import com.school.requestModel.lesson.StudentLessonProgressRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface StudentLessonProgressEntryPointMapper {

    companion object {
        val INSTANCE: StudentLessonProgressEntryPointMapper = Mappers.getMapper(StudentLessonProgressEntryPointMapper::class.java)
    }

    @Mapping(target = "id", ignore = true)
    @Mappings(
        Mapping(source = "studentId", target = "student.id"),
        Mapping(source = "lessonId", target = "lesson.id")
    )
    fun toDomainByRequest(classProgressRequest: StudentLessonProgressRequest): StudentLessonProgressDomain
}