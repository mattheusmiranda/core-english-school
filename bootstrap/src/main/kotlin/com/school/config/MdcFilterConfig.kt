package com.school.config

import com.school.filter.ProcessIdFilter
import com.school.logging.ProcessIdGenerator
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuração Spring para registrar o [ProcessIdFilter].
 * Isso garante que o filtro seja aplicado a todas as requisições.
 */
@Configuration
class MdcFilterConfig(
    private val processIdGenerator: ProcessIdGenerator
) {
    @Bean
    fun processIdFilterRegistration(): FilterRegistrationBean<ProcessIdFilter> {
        val registrationBean = FilterRegistrationBean<ProcessIdFilter>()
        registrationBean.filter = ProcessIdFilter(processIdGenerator)
        registrationBean.order = 1 // Define uma ordem alta para garantir que seja executado cedo
        return registrationBean
    }
}
