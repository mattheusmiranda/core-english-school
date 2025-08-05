package com.school.mapper.student

import com.school.domain.StudentDomain
import com.school.domain.StudentPutRequestDomain
import com.school.requestModel.student.CreateStudentRequest
import com.school.requestModel.student.StudentPutRequest
import com.school.responseModel.student.StudentResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
interface StudentEntryPointMapper {

    // Ele é como o static do Java, dessa forma o INSTANCE pertence à classe, não a uma instância específica
    companion object {
        val INSTANCE: StudentEntryPointMapper = Mappers.getMapper(StudentEntryPointMapper::class.java)
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDate.now())")
    fun toDomainByCreateRequest(request: CreateStudentRequest): StudentDomain

    fun toResponse(domain: StudentDomain): StudentResponse

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toDomainByPutRequest(request: StudentPutRequest): StudentDomain

    fun toStudentPutRequestDomain(putRequest: StudentPutRequest): StudentPutRequestDomain

}