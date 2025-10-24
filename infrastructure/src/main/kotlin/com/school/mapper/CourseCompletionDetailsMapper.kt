package com.school.mapper

import com.school.domain.CourseCompletionDetailsDomain
import com.school.domain.StudentLessonProgressDomain
import com.school.entity.CourseCompletionDetails
import com.school.entity.StudentLessonProgress
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CourseCompletionDetailsMapper {

    fun toEntity(entity: CourseCompletionDetailsDomain): CourseCompletionDetails

}