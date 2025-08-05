package com.school.exception

import com.school.exception.student.StudentDeleteException
import com.school.exception.student.StudentFindException
import com.school.exception.student.StudentPutException
import com.school.exception.student.StudentSaveException
import com.school.restController.StudentRestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice(assignableTypes = [StudentRestController::class])
class StudentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val firstError = ex.bindingResult
            .fieldErrors
            .first()
            ?.defaultMessage
            ?: "Invalid request."

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(mapOf("message" to firstError))
    }

    @ExceptionHandler(UniqueConstraintViolationException::class)
    fun handleUniqueViolation(ex: UniqueConstraintViolationException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(mapOf("message" to (ex.message ?: "Invalid field")))
    }


    @ExceptionHandler(StudentSaveException::class)
    fun handleStudentSave(ex: StudentSaveException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.message)
    }

    @ExceptionHandler(StudentFindException::class)
    fun handleStudentFind(ex: StudentFindException): ResponseEntity<Map<String, String>?> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(mapOf("message" to (ex.message ?: "Not found")))
    }

    @ExceptionHandler(StudentPutException::class)
    fun handleStudentPutException(ex: StudentPutException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(mapOf("message" to (ex.message ?: "Error updating student")))
    }

    @ExceptionHandler(StudentDeleteException::class)
    fun handleStudentDeleteException(ex: StudentDeleteException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(mapOf("message" to (ex.message ?: "Error deleting student")))
    }

}
