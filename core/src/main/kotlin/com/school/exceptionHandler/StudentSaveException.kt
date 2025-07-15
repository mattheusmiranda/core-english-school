package com.school.exceptionHandler

class StudentSaveException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
