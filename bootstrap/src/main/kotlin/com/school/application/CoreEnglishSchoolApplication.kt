package com.school.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.school"])
@EnableJpaRepositories("com.school.jpaRepository")
@EntityScan("com.school.entity")
class CoreEnglishSchoolApplication

fun main(args: Array<String>) {
	runApplication<CoreEnglishSchoolApplication>(*args)
}
