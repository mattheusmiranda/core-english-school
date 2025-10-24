package com.school.jpaRepository

import com.school.entity.CourseCompletionDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseCompletionDetailsJpaRepository : JpaRepository<CourseCompletionDetails, Int>{
}