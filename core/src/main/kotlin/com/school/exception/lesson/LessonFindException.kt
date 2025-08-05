package com.school.exception.lesson

class LessonFindException (
    message: String,
    cause: Throwable? = null
) : NoSuchElementException(message, cause)