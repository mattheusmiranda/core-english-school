import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.kafka.core.KafkaTemplate
import org.flywaydb.core.Flyway
import io.micrometer.prometheus.PrometheusMeterRegistry
import jakarta.persistence.Entity
import jakarta.persistence.Id

class InfrastructureHealthChecker(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val flyway: Flyway,
    private val prometheusRegistry: PrometheusMeterRegistry
) {
    //comentario para commitar
    fun checkAll() {
        // Jackson
        val mapper = jacksonObjectMapper()
        val json = mapper.writeValueAsString(mapOf("status" to "ok"))
        println("Jackson Test: $json")

        // Kafka
        kafkaTemplate.send("test-topic", "Infra is alive")

        // Flyway
        println("Flyway Version: ${flyway.info().current()?.version}")

        // Micrometer Prometheus
        println("Prometheus Metrics: ${prometheusRegistry.scrape().take(100)}")
    }
}

@Entity
data class DummyEntity(
    @Id val id: Long,
    val name: String
)

interface DummyRepository : JpaRepository<DummyEntity, Long>
