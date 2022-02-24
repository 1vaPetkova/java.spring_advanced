package com.example.hateoas_demo.services;

import com.example.hateoas_demo.model.dto.StudentDto;

import java.util.List;

public interface StudentService {


    StudentDto findById(Long studentId);

    List<StudentDto> allStudentsDto();
}
