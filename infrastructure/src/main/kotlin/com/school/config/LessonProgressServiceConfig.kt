package com.school.config

import com.school.port.output.LessonCompletedEventPublisher
import com.school.usecase.LessonProgressService
import com.school.usecase.LessonService
import com.school.usecase.StudentLessonProgressServiceJpa
import com.school.usecase.StudentService
import com.school.usecase.impl.LessonProgressServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LessonProgressServiceConfig {

    @Bean
    fun lessonProgressService(
        publisher: LessonCompletedEventPublisher,
        studentService: StudentService,
        lessonService: LessonService,
        studentLessonProgressServiceJpa: StudentLessonProgressServiceJpa
    ): LessonProgressService {
        return LessonProgressServiceImpl(
            publisher = publisher,
            studentService = studentService,
            lessonService = lessonService,
            studentLessonProgressServiceJpa = studentLessonProgressServiceJpa
        )
    }

}