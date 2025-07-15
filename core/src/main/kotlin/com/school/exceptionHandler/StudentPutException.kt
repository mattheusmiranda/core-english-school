package com.school.exceptionHandler

class StudentPutException (
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)