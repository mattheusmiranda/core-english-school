package com.school.producer

import com.school.avro.StudentLessonProgressRecord
import com.school.config.KafkaTopicsConfig
import com.school.domain.StudentLessonProgressDomain
import com.school.mapper.AvroMapper
import com.school.port.output.LessonCompletedEventPublisher
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class LessonCompletedKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, StudentLessonProgressRecord>,
    private val avroMapper: AvroMapper,
    private val kafkaTopicsConfig: KafkaTopicsConfig
) : LessonCompletedEventPublisher {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val topic = kafkaTopicsConfig.lessonCompleted

    override fun publish(studentLessonProgressDomain: StudentLessonProgressDomain) {
        try {
            MDC.put("topic", topic)
            MDC.put("studentId", studentLessonProgressDomain.student.id.toString())

            val avroRecord = avroMapper.toStudentLessonProgressRecord(studentLessonProgressDomain)
            kafkaTemplate.send(topic, avroRecord.studentRecord.id.toString(), avroRecord)

            logger.info("Posting message to topic")
        } catch (e: Exception) {
            logger.error("Error publishing message to topic", e)
        } finally {
            MDC.clear()
        }
    }

}