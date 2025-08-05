package com.school.jpaRepository

import com.school.entity.StudentLessonProgress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface StudentLessonProgressJpaRepository : JpaRepository<StudentLessonProgress, Int> {
    fun findByStudentIdAndLessonId(studentId: Int, lessonId: Int): Optional<StudentLessonProgress>
}