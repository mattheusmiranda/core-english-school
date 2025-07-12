package com.school.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

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
}

