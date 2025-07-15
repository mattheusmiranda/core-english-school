package com.school.exceptionHandler

class StudentFindException (
    message: String,
    cause: Throwable? = null
) : NoSuchElementException(message, cause)