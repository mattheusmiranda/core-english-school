package com.school.adapter

import com.school.domain.CourseCompletionDetailsDomain
import com.school.jpaRepository.CourseCompletionDetailsJpaRepository
import com.school.mapper.CourseCompletionDetailsMapper
import com.school.port.output.CourseCompletionDetailsPort
import org.springframework.stereotype.Repository

@Repository
class CourseCompletionDetailsRepositoryAdapter(
    private val courseCompletionDetailsJpaRepository: CourseCompletionDetailsJpaRepository,
    private val courseCompletionDetailsMapper: CourseCompletionDetailsMapper
): CourseCompletionDetailsPort {
    override fun updateCourseCompletionDetails(courseCompletionDetailsDomain: CourseCompletionDetailsDomain) {
        val entity = courseCompletionDetailsMapper.toEntity(courseCompletionDetailsDomain)
        courseCompletionDetailsJpaRepository.save(entity)
    }
}