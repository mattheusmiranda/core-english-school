package com.school.core.logging

import org.slf4j.Logger
import org.slf4j.MDC

inline fun <reified T> Logger.infoWithContext(message: String) {
    try {
        val stackTrace = Thread.currentThread().stackTrace
        val callingMethod = stackTrace.getOrNull(2)
        
        MDC.put(LoggingConstants.CLASS_NAME, T::class.java.simpleName)
        MDC.put(LoggingConstants.METHOD_NAME, callingMethod?.methodName ?: "unknown")
        
        this.info(message)
    } finally {
        MDC.remove(LoggingConstants.CLASS_NAME)
        MDC.remove(LoggingConstants.METHOD_NAME)
    }
}

inline fun <reified T> Logger.errorWithContext(message: String, throwable: Throwable? = null) {
    try {
        val stackTrace = Thread.currentThread().stackTrace
        val callingMethod = stackTrace.getOrNull(2)
        
        MDC.put(LoggingConstants.CLASS_NAME, T::class.java.simpleName)
        MDC.put(LoggingConstants.METHOD_NAME, callingMethod?.methodName ?: "unknown")
        
        if (throwable != null) {
            this.error(message, throwable)
        } else {
            this.error(message)
        }
    } finally {
        MDC.remove(LoggingConstants.CLASS_NAME)
        MDC.remove(LoggingConstants.METHOD_NAME)
    }
}

inline fun <reified T> Logger.warnWithContext(message: String) {
    try {
        val stackTrace = Thread.currentThread().stackTrace
        val callingMethod = stackTrace.getOrNull(2)
        
        MDC.put(LoggingConstants.CLASS_NAME, T::class.java.simpleName)
        MDC.put(LoggingConstants.METHOD_NAME, callingMethod?.methodName ?: "unknown")
        
        this.warn(message)
    } finally {
        MDC.remove(LoggingConstants.CLASS_NAME)
        MDC.remove(LoggingConstants.METHOD_NAME)
    }
}

inline fun <reified T> Logger.debugWithContext(message: String) {
    try {
        val stackTrace = Thread.currentThread().stackTrace
        val callingMethod = stackTrace.getOrNull(2)
        
        MDC.put(LoggingConstants.CLASS_NAME, T::class.java.simpleName)
        MDC.put(LoggingConstants.METHOD_NAME, callingMethod?.methodName ?: "unknown")
        
        this.debug(message)
    } finally {
        MDC.remove(LoggingConstants.CLASS_NAME)
        MDC.remove(LoggingConstants.METHOD_NAME)
    }
}