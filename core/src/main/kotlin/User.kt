import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class User(
    @Id
    val id: Long,
    val name: String
) {
    fun sayHello(): String {
        return "Hello, $name!"
    }
}