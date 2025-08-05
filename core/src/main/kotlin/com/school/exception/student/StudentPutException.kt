package com.school.exception.student

class StudentPutException (
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)