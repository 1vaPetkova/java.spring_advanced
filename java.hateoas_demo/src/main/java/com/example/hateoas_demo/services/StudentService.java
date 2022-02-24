package com.example.hateoas_demo.services;

import com.example.hateoas_demo.model.dto.OrderDto;
import com.example.hateoas_demo.model.dto.StudentDto;
import com.example.hateoas_demo.model.entities.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {


    StudentDto findById(Long studentId);

    List<StudentDto> allStudentsDto();

    List<OrderDto> getOrdersDto(Long studentId);
}
