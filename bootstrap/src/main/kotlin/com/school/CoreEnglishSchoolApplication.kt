package com.school

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class CoreEnglishSchoolApplication

fun main(args: Array<String>) {
	runApplication<CoreEnglishSchoolApplication>(*args)
}
