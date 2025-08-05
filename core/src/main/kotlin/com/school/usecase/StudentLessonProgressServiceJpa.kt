package com.school.usecase

import com.school.domain.StudentLessonProgressDomain

interface StudentLessonProgressServiceJpa {
    fun findByStudentIdAndLessonId(studentId: Int, lessonId: Int): StudentLessonProgressDomain
}