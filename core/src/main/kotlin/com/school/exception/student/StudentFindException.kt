package com.school.exception.student

class StudentFindException (
    message: String,
    cause: Throwable? = null
) : NoSuchElementException(message, cause)