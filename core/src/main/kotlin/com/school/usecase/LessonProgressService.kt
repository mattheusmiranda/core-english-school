package com.school.usecase

import com.school.domain.StudentLessonProgressDomain

interface LessonProgressService {
    fun registerLessonCompletion(studentId: Int, lessonId: Int, newPercentageClass: Int): StudentLessonProgressDomain
    fun classBelongsToTheStudent(studentId: Int, lessonId: Int): StudentLessonProgressDomain
}