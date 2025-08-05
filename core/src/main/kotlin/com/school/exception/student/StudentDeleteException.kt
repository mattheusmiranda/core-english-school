package com.school.exception.student

class StudentDeleteException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
