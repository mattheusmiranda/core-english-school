# Padrão de Log Padronizado

Este projeto implementa um padrão de log padronizado que garante que todos os logs contenham informações consistentes sobre **process_id**, **classe** e **método**.

## Como Funciona

### Process ID por Thread
- Cada requisição HTTP recebe um **UUID único** como `process_id`
- O `process_id` é mantido no **MDC (Mapped Diagnostic Context)** durante toda a thread
- Para operações assíncronas, o contexto é propagado automaticamente

### Informações Automáticas no Log
Cada log conterá:
- **process_id**: UUID único por fluxo/thread
- **className**: Nome da classe que fez o log
- **methodName**: Nome do método que fez o log
- **timestamp**, **thread**, **level** e **mensagem**

## Como Usar

### 1. Importe as extensões de log
```kotlin
import com.school.core.logging.*
import org.slf4j.LoggerFactory
```

### 2. Declare o logger
```kotlin
class MinhaClasse {
    private val logger = LoggerFactory.getLogger(MinhaClasse::class.java)
}
```

### 3. Use as extensões de log com contexto
```kotlin
class ExemploController {
    private val logger = LoggerFactory.getLogger(ExemploController::class.java)
    
    fun meuMetodo() {
        // Log de informação
        logger.infoWithContext<ExemploController>("Iniciando processamento")
        
        try {
            // Seu código aqui
            logger.debugWithContext<ExemploController>("Debug durante processamento")
            
        } catch (e: Exception) {
            // Log de erro com exception
            logger.errorWithContext<ExemploController>("Erro no processamento", e)
        }
        
        // Log de warning
        logger.warnWithContext<ExemploController>("Atenção: condição inesperada")
    }
}
```

## Métodos Disponíveis

- `logger.infoWithContext<SuaClasse>(message)`
- `logger.errorWithContext<SuaClasse>(message, throwable?)`
- `logger.warnWithContext<SuaClasse>(message)`
- `logger.debugWithContext<SuaClasse>(message)`

## Formato de Saída

```
2024-01-15 10:30:45.123 [http-nio-8080-exec-1] INFO [a1b2c3d4-e5f6-7890-abcd-ef1234567890] [ExemploController] [meuMetodo] com.school.ExemploController - Iniciando processamento
```

## Configuração para Operações Assíncronas

Para operações assíncronas, use a anotação `@Async` e o executor configurado:

```kotlin
@Service
class MinhaService {
    private val logger = LoggerFactory.getLogger(MinhaService::class.java)
    
    @Async("taskExecutor")
    fun processoAssincrono() {
        // O process_id será propagado automaticamente
        logger.infoWithContext<MinhaService>("Processamento assíncrono iniciado")
    }
}
```

## Arquivos Implementados

### Core Module
- `LoggingConstants.kt` - Constantes para chaves MDC
- `ProcessIdGenerator.kt` - Gerador de UUID para process_id
- `LoggerExtensions.kt` - Extensões do Logger com contexto automático
- `LoggerExample.kt` - Exemplo de uso do padrão

### Infrastructure Module  
- `ProcessIdFilter.kt` - Filtro servlet para requests HTTP
- `ProcessIdInterceptor.kt` - Interceptor alternativo 
- `AsyncTaskExecutorDecorator.kt` - Decorator para tarefas assíncronas

### Bootstrap Module
- `LoggingConfiguration.kt` - Configuração Spring Boot
- `logback-spring.xml` - Configuração do Logback com padrão MDC

## Dependências Adicionadas

No `core/build.gradle.kts`:
```kotlin
implementation("org.slf4j:slf4j-api:2.0.9")
```

## Observações Importantes

1. **Always use the generic type**: Sempre passe a classe atual como tipo genérico nas extensões de log
2. **Thread safety**: O padrão é thread-safe usando MDC
3. **Performance**: As informações de classe/método são obtidas via stack trace
4. **Async support**: Contexto é automaticamente propagado em operações assíncronas
5. **Cleanup**: MDC é limpo automaticamente após cada request/operação