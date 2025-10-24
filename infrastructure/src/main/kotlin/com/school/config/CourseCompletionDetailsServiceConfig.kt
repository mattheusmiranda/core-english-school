package com.school.config

import com.school.port.output.CourseCompletionDetailsPort
import com.school.usecase.CourseCompletionDetailsService
import com.school.usecase.impl.CourseCompletionDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CourseCompletionDetailsServiceConfig {

    @Bean
    fun updateCourseCompletionDetails(
        courseCompletionDetailsPort: CourseCompletionDetailsPort
    ): CourseCompletionDetailsService {
        return CourseCompletionDetailsServiceImpl(
            courseCompletionDetailsPort = courseCompletionDetailsPort
        )
    }
}