package com.school.core.logging

import org.slf4j.LoggerFactory

/**
 * Exemplo de como usar o padrão de log implementado.
 * 
 * Para usar o padrão de log padronizado:
 * 1. Injete o Logger do SLF4J
 * 2. Use as extensões infoWithContext, errorWithContext, etc.
 * 3. Passe a classe atual como tipo genérico
 */
class LoggerExample {
    
    private val logger = LoggerFactory.getLogger(LoggerExample::class.java)
    
    fun exemploDeUso() {
        // Log de informação com contexto automático
        logger.infoWithContext<LoggerExample>("Iniciando processamento do exemplo")
        
        try {
            // Simula algum processamento
            processoComLog()
            
            logger.infoWithContext<LoggerExample>("Processamento concluído com sucesso")
        } catch (e: Exception) {
            logger.errorWithContext<LoggerExample>("Erro durante processamento", e)
        }
    }
    
    private fun processoComLog() {
        logger.debugWithContext<LoggerExample>("Executando processo interno")
        
        // Simula uma condição de warning
        val condicaoDeWarning = true
        if (condicaoDeWarning) {
            logger.warnWithContext<LoggerExample>("Condição de warning detectada")
        }
        
        // Pode lançar uma exception para demonstrar o log de erro
        // throw RuntimeException("Erro simulado")
    }
}