package com.school.exception.student

class StudentSaveException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
