package com.school.port.output

import com.school.domain.CourseCompletionDetailsDomain

interface CourseCompletionDetailsPort {
    fun updateCourseCompletionDetails(courseCompletionDetailsDomain: CourseCompletionDetailsDomain)
}