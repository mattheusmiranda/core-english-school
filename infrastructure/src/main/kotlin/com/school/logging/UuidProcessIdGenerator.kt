package com.school.logging

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UuidProcessIdGenerator : ProcessIdGenerator {
    override fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
