package com.school.exceptionHandler

class StudentDeleteException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
