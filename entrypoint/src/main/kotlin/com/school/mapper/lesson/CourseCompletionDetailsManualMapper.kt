package com.school.mapper.lesson

import com.school.avro.CourseCompletionDetailsRecord
import com.school.domain.CourseCompletionDetailsDomain
import org.springframework.stereotype.Component

@Component
class CourseCompletionDetailsManualMapper {
    fun toDomain(courseCompletionDetailsRecord: CourseCompletionDetailsRecord) : CourseCompletionDetailsDomain {
        return CourseCompletionDetailsDomain(
            studentLessonProgressId = courseCompletionDetailsRecord.studentLessonProgressId,
            totalClasses = courseCompletionDetailsRecord.totalClasses,
            completedClasses = courseCompletionDetailsRecord.completedClasses,
            remainingClasses = courseCompletionDetailsRecord.remainingClasses,
            percentageCompleted = courseCompletionDetailsRecord.percentageCompleted,
            percentageRemaining = courseCompletionDetailsRecord.percentageRemaining
        )
    }
}