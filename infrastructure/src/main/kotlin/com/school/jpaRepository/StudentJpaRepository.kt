package com.school.jpaRepository

import com.school.entity.Student
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface StudentJpaRepository : JpaRepository<Student, Long>