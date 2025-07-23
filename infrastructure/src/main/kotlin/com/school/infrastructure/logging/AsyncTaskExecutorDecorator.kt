package com.school.infrastructure.logging

import com.school.core.logging.LoggingConstants
import com.school.core.logging.ProcessIdGenerator
import org.slf4j.MDC
import org.springframework.core.task.TaskDecorator

class AsyncTaskExecutorDecorator : TaskDecorator {

    override fun decorate(runnable: Runnable): Runnable {
        val contextMap = MDC.getCopyOfContextMap() ?: mutableMapOf()
        
        // Se não existe process_id no contexto, gera um novo
        if (!contextMap.containsKey(LoggingConstants.PROCESS_ID)) {
            contextMap[LoggingConstants.PROCESS_ID] = ProcessIdGenerator.generate()
        }
        
        return Runnable {
            try {
                MDC.setContextMap(contextMap)
                runnable.run()
            } finally {
                MDC.clear()
            }
        }
    }
}