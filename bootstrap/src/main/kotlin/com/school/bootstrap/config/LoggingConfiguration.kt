package com.school.bootstrap.config

import com.school.infrastructure.logging.AsyncTaskExecutorDecorator
import com.school.infrastructure.logging.ProcessIdInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableAsync
class LoggingConfiguration(
    private val processIdInterceptor: ProcessIdInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(processIdInterceptor)
    }

    @Bean(name = ["taskExecutor"])
    fun taskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 4
        executor.maxPoolSize = 8
        executor.queueCapacity = 500
        executor.setThreadNamePrefix("async-")
        executor.setTaskDecorator(AsyncTaskExecutorDecorator())
        executor.initialize()
        return executor
    }
}