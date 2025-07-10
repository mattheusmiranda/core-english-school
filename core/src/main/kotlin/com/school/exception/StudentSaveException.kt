package com.school.exception

class StudentSaveException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
