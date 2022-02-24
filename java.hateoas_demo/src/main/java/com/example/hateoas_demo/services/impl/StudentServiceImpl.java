package com.example.hateoas_demo.services.impl;

import com.example.hateoas_demo.mapping.StudentMapper;
import com.example.hateoas_demo.model.dto.StudentDto;
import com.example.hateoas_demo.repositories.StudentRepository;
import com.example.hateoas_demo.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    @Override
    public StudentDto findById(Long studentId) {
        return this.studentRepository
                .findById(studentId)
                .map(studentMapper::mapEntityToDto)
                .orElse(null);
    }

    @Override
    public List<StudentDto> allStudentsDto() {
        return this.studentRepository.findAll()
                .stream()
                .map(studentMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
