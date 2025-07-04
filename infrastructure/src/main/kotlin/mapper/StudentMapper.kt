package mapper

import domain.StudentDomain
import entity.Student
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface StudentMapper {

    companion object {
        val INSTANCE: StudentMapper = Mappers.getMapper(StudentMapper::class.java)
    }

    fun toEntity(model: StudentDomain): Student

    fun toModel(entity: Student): StudentDomain

    /* Exemplos caso precisar ignorar um campo ou há um campo que em um objeto é diferente do outro.
        @Mapping(source = "idStudent", target = "id") // campos equivalentes mas com nomes diferentes
        @Mapping(target = "active", ignore = true) // Ignora o campo extra
        fun toEntity(domain: StudentDomain): Student
    */
}
