package mapper

import domain.StudentDomain
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import requestModel.CreateStudentRequest
import responseModel.StudentResponse

@Mapper
interface StudentEntryPointMapper {

    // Ele é como o static do Java, dessa forma o INSTANCE pertence à classe, não a uma instância específica
    companion object {
        val INSTANCE: StudentEntryPointMapper = Mappers.getMapper(StudentEntryPointMapper::class.java)
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    fun toDomain(request: CreateStudentRequest): StudentDomain

    fun toResponse(domain: StudentDomain): StudentResponse
}