package com.school.usecase.impl

import com.school.domain.CourseCompletionDetailsDomain
import com.school.port.output.CourseCompletionDetailsPort
import com.school.usecase.CourseCompletionDetailsService

class CourseCompletionDetailsServiceImpl(
    private val courseCompletionDetailsPort: CourseCompletionDetailsPort
): CourseCompletionDetailsService {
    override fun updateCourseCompletionDetails(courseCompletionDetailsDomain: CourseCompletionDetailsDomain) {
        courseCompletionDetailsPort.updateCourseCompletionDetails(courseCompletionDetailsDomain)
    }
}