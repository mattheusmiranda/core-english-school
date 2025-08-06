package com.school.mapper

import com.school.avro.LessonRecord
import com.school.avro.StudentLessonProgressRecord
import com.school.avro.StudentRecord
import com.school.domain.StudentLessonProgressDomain
import org.springframework.stereotype.Component

@Component
class AvroMapper {

    fun toStudentLessonProgressRecord(domain: StudentLessonProgressDomain): StudentLessonProgressRecord {
        return StudentLessonProgressRecord.newBuilder()
            .setId(domain.id)
            .setStudentRecord(
                StudentRecord.newBuilder()
                    .setId(domain.student.id)
                    .setName(domain.student.name)
                    .setEmail(domain.student.email)
                    .setUpdatedAt(domain.student.updatedAt)
                    .setCreatedAt(domain.student.createdAt)
                    .build()
            )
            .setLessonRecord(
                LessonRecord.newBuilder()
                    .setId(domain.lesson.id)
                    .setCourseId(domain.lesson.courseId)
                    .setTitle(domain.lesson.title)
                    .setDurationSeconds(domain.lesson.durationSeconds)
                    .setCreatedAt(domain.lesson.createdAt)
                    .setUpdatedAt(domain.lesson.updatedAt)
                    .build()
            )
            .setPercentageOfProgress(domain.percentageOfProgress)
            .setCompletedAt(domain.completedAt)
            .build()
    }
}
