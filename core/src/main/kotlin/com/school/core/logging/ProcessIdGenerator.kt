package com.school.core.logging

import java.util.UUID

object ProcessIdGenerator {
    fun generate(): String = UUID.randomUUID().toString()
}