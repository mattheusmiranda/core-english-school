package com.school.mapper

import com.school.domain.StudentDomain
import com.school.domain.StudentPutRequestDomain
import com.school.entity.Student
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper
interface StudentMapper {

    companion object {
        val INSTANCE: StudentMapper = Mappers.getMapper(StudentMapper::class.java)
    }

    fun toEntity(model: StudentDomain): Student

    fun toModel(entity: Student): StudentDomain

    // O @MappingTarget indica que o MapStruct deve atualizar este objeto existente (entity)
    // em vez de criar uma nova instância.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun updateEntityFromPutRequest(request: StudentPutRequestDomain, @MappingTarget entity: Student): Student

    /* Exemplos caso precisar ignorar um campo ou há um campo que em um objeto é diferente do outro.
        @Mapping(source = "idStudent", target = "id") // campos equivalentes mas com nomes diferentes
        @Mapping(target = "active", ignore = true) // Ignora o campo extra
        fun toEntity(domain: StudentDomain): Student
    */
}
