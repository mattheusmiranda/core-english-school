package controller

import mapper.StudentEntryPointMapper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import requestModel.CreateStudentRequest
import responseModel.StudentResponse
import usecase.StudentService
import java.util.logging.Logger

@RestController
@RequestMapping("api/students")
class StudentController(
    private val studentService: StudentService
) {

    @PostMapping
    fun create(@RequestBody request: CreateStudentRequest): ResponseEntity<StudentResponse> {
        val studentDomain = StudentEntryPointMapper.Companion.INSTANCE.toDomain(request)
        val created = studentService.create(studentDomain)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(StudentEntryPointMapper.Companion.INSTANCE.toResponse(created))
    }

}