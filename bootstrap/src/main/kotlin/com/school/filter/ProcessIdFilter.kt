package com.school.filter

import com.school.logging.ProcessIdGenerator
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.MDC
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1) // Garante que este filtro seja executado no início da cadeia de filtros
class ProcessIdFilter(
    private val processIdGenerator: ProcessIdGenerator
) : Filter {

    companion object {
        const val PROCESS_ID_KEY = "process_id"
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val processId = processIdGenerator.generate()
        MDC.put(PROCESS_ID_KEY, processId)

        try {
            chain.doFilter(request, response) // Continua o processamento da requisição
        } finally {
            MDC.remove(PROCESS_ID_KEY)
        }
    }

}
