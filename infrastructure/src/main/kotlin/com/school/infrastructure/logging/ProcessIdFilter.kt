package com.school.infrastructure.logging

import com.school.core.logging.LoggingConstants
import com.school.core.logging.ProcessIdGenerator
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component

@Component
class ProcessIdFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val processId = ProcessIdGenerator.generate()
        
        try {
            MDC.put(LoggingConstants.PROCESS_ID, processId)
            chain.doFilter(request, response)
        } finally {
            MDC.clear()
        }
    }
}