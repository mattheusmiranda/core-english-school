package com.school.consumer

import com.school.avro.CourseCompletionDetailsRecord
import com.school.mapper.lesson.CourseCompletionDetailsManualMapper
import com.school.usecase.CourseCompletionDetailsService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CourseCompletionDetailsKafkaConsumer(
    private val courseCompletionDetailsManualMapper: CourseCompletionDetailsManualMapper,
    private val courseCompletionDetailsService: CourseCompletionDetailsService
) {

    private val logger = LoggerFactory.getLogger(CourseCompletionDetailsKafkaConsumer::class.java)

    @KafkaListener(
        topics = ["student_progress_updated"],
        groupId = "student-progress-updated-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consume(record: ConsumerRecord<String, CourseCompletionDetailsRecord>) {
        val courseCompletionDetailsDomain = courseCompletionDetailsManualMapper
            .toDomain(record.value())
        courseCompletionDetailsService.updateCourseCompletionDetails(courseCompletionDetailsDomain)
        toLog(record)
    }

    fun toLog(record: ConsumerRecord<String, CourseCompletionDetailsRecord>) {
        MDC.put("studentLessonProgressId", record.value().studentLessonProgressId.toString())
        MDC.put("topic", record.topic())
        MDC.put("key", record.key())
        logger.info("Topic consumption 'student_progress_updated'")
    }
}