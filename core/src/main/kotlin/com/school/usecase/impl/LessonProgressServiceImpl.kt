package com.school.usecase.impl

import com.school.domain.StudentLessonProgressDomain
import com.school.port.output.LessonCompletedEventPublisher
import com.school.usecase.LessonProgressService
import com.school.usecase.LessonService
import com.school.usecase.StudentLessonProgressServiceJpa
import com.school.usecase.StudentService
import java.time.LocalDate

class LessonProgressServiceImpl(
    private val publisher: LessonCompletedEventPublisher,
    private val studentService: StudentService,
    private val lessonService: LessonService,
    private val studentLessonProgressServiceJpa: StudentLessonProgressServiceJpa
) : LessonProgressService {

    override fun registerLessonCompletion(studentId: Int, lessonId: Int, newPercentageClass: Int): StudentLessonProgressDomain {
        val completedAt = LocalDate.now()

       val student = studentService.getById(studentId)
       val lesson = lessonService.getById(lessonId)

       val studentLessonProgressDomain = classBelongsToTheStudent(student.id, lesson.id)

       val event = StudentLessonProgressDomain(
           studentLessonProgressDomain.id,
           studentLessonProgressDomain.student,
           studentLessonProgressDomain.lesson,
           newPercentageClass,
           completedAt
       )

       publisher.publish(event)

       return event
    }

    override fun classBelongsToTheStudent(studentId: Int, lessonId: Int): StudentLessonProgressDomain {
        return studentLessonProgressServiceJpa.findByStudentIdAndLessonId(studentId, lessonId)
    }

}
