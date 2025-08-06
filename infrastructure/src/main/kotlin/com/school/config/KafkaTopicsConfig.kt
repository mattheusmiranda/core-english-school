package com.school.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KafkaTopicsConfig(
    @Value("\${topics.lesson-completed}")
    val lessonCompleted: String
)
