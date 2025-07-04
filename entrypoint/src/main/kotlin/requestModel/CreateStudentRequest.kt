package requestModel

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

data class CreateStudentRequest(
    @field:NotNull
    val name: String,

    @field:Email
    @field:NotNull
    val email: String
)