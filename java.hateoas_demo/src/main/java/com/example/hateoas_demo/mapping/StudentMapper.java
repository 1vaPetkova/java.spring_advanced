package com.example.hateoas_demo.mapping;


import com.example.hateoas_demo.model.dto.StudentDto;
import com.example.hateoas_demo.model.entities.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto mapEntityToDto(StudentEntity student);

}
