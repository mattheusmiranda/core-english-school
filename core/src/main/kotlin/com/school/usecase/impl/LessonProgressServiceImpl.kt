package com.school.usecase.impl

import com.school.domain.StudentLessonProgressDomain
import com.school.port.output.LessonCompletedEventPublisher
import com.school.usecase.LessonProgressService
import com.school.usecase.LessonService
import com.school.usecase.StudentLessonProgressServiceJpa
import com.school.usecase.StudentService
import java.time.LocalDateTime

class LessonProgressServiceImpl(
    private val publisher: LessonCompletedEventPublisher,
    private val studentService: StudentService,
    private val lessonService: LessonService,
    private val studentLessonProgressServiceJpa: StudentLessonProgressServiceJpa
) : LessonProgressService {

    override fun registerLessonCompletion(studentId: Int, lessonId: Int, newPercentageClass: Int): StudentLessonProgressDomain {
        val completedAt = LocalDateTime.now()

       val student = studentService.getById(studentId)
       val lesson = lessonService.getById(lessonId)

       classBelongsToTheStudent(student.id, lesson.id)

       val event = StudentLessonProgressDomain(
           student,
           lesson,
           newPercentageClass,
           completedAt
       )

       publisher.publish(event)

       return event
    }

    override fun classBelongsToTheStudent(studentId: Int, lessonId: Int): Boolean {
        studentLessonProgressServiceJpa.findByStudentIdAndLessonId(studentId, lessonId)
        return true
    }

}
