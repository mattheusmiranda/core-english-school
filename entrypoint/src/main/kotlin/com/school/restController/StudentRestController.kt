package com.school.restController

import com.school.mapper.student.StudentEntryPointMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.school.requestModel.student.CreateStudentRequest
import com.school.requestModel.student.StudentPutRequest
import com.school.responseModel.student.StudentResponse
import com.school.usecase.StudentService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping

@RestController
@RequestMapping("api/students")
class StudentRestController(
    private val studentService: StudentService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun create(@RequestBody @Valid request: CreateStudentRequest): ResponseEntity<StudentResponse> {
        MDC.put("name", request.name)
        val studentDomain = StudentEntryPointMapper.Companion.INSTANCE.toDomainByCreateRequest(request)
        val created = studentService.create(studentDomain)
        logger.info("lugar ao sol")
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(StudentEntryPointMapper.Companion.INSTANCE.toResponse(created))
    }

    @GetMapping( "/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<StudentResponse> {
        val student = studentService.getById(id)
        return ResponseEntity.status(HttpStatus.OK)
            .body(StudentEntryPointMapper.Companion.INSTANCE.toResponse(student))
    }

    @PutMapping("/{id}")
    fun updateStudent(
        @PathVariable id: Int,
        @RequestBody request: StudentPutRequest
    ): ResponseEntity<StudentResponse> {
        val putRequest = StudentEntryPointMapper.Companion.INSTANCE.toStudentPutRequestDomain(request)
        val updated = studentService.update(id, putRequest)
        return ResponseEntity.status(HttpStatus.OK)
            .body(StudentEntryPointMapper.INSTANCE.toResponse(updated))
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Int): ResponseEntity<Void> {
        studentService.delete(id)
        return ResponseEntity.noContent().build()
    }

}