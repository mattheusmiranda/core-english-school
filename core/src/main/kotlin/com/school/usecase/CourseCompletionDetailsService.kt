package com.school.usecase

import com.school.domain.CourseCompletionDetailsDomain

interface CourseCompletionDetailsService {
    fun updateCourseCompletionDetails(courseCompletionDetailsDomain: CourseCompletionDetailsDomain)
}