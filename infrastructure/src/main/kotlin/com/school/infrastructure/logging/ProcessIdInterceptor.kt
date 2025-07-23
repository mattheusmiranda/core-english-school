package com.school.infrastructure.logging

import com.school.core.logging.LoggingConstants
import com.school.core.logging.ProcessIdGenerator
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class ProcessIdInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val processId = ProcessIdGenerator.generate()
        MDC.put(LoggingConstants.PROCESS_ID, processId)
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        MDC.clear()
    }
}