package com.school.port.output

import com.school.domain.StudentLessonProgressDomain

interface LessonCompletedEventPublisher {
    fun publish(event: StudentLessonProgressDomain)
}
