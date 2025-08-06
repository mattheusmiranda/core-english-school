package com.school.producer

import com.school.avro.StudentLessonProgressRecord
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
    private val avroMapper: AvroMapper
) : LessonCompletedEventPublisher {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val topic = "lesson_completed"

    override fun publish(studentLessonProgressDomain: StudentLessonProgressDomain) {
        try {
            val avroRecord = avroMapper.toStudentLessonProgressRecord(studentLessonProgressDomain)
            kafkaTemplate.send(topic, avroRecord.studentRecord.id.toString(), avroRecord)

            logger.info(
                "message=\"Posting message to topic $topic para studentId=${studentLessonProgressDomain.student.id}\""
            )
        } catch (e: Exception) {
            logger.error("message=\"Error publishing message to topic $topic for studentId=${studentLessonProgressDomain.student.id}\"", e)
        } finally {
            MDC.clear()
        }
    }
}