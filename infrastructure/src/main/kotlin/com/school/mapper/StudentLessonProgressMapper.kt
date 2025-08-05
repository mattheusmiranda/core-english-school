package com.school.mapper

import com.school.avro.LessonRecord
import com.school.avro.StudentLessonProgressRecord
import com.school.avro.StudentRecord
import com.school.domain.StudentLessonProgressDomain
import com.school.entity.StudentLessonProgress
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface StudentLessonProgressMapper {

    companion object {
        val INSTANCE: StudentLessonProgressMapper = Mappers.getMapper(StudentLessonProgressMapper::class.java)
    }

    fun toDomain(entity: StudentLessonProgress): StudentLessonProgressDomain

    fun toAvro(domain: StudentLessonProgressDomain): StudentLessonProgressRecord {
        return StudentLessonProgressRecord.newBuilder()
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
                    .build()
            )
            .setCompletedAt(domain.completedAt.toString())
            .build()
    }
}