package com.school.producer

import com.school.avro.StudentLessonProgressRecord
import com.school.domain.StudentLessonProgressDomain
import com.school.mapper.StudentLessonProgressMapper
import com.school.port.output.LessonCompletedEventPublisher
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class LessonCompletedKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, StudentLessonProgressRecord>,
    private val avroMapper: StudentLessonProgressMapper
) : LessonCompletedEventPublisher {

    override fun publish(event: StudentLessonProgressDomain) {
        val avro = avroMapper.toAvro(event)
        kafkaTemplate.send("lesson_completed", avro)
    }
}

