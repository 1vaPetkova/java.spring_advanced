package com.example.hateoas_demo.services.impl;

import com.example.hateoas_demo.model.dto.OrderDto;
import com.example.hateoas_demo.model.entities.StudentEntity;
import com.example.hateoas_demo.model.mapping.OrderMapper;
import com.example.hateoas_demo.model.mapping.StudentMapper;
import com.example.hateoas_demo.model.dto.StudentDto;
import com.example.hateoas_demo.repositories.StudentRepository;
import com.example.hateoas_demo.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final OrderMapper orderMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, OrderMapper orderMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.orderMapper = orderMapper;
    }


    @Override
    public StudentDto findById(Long studentId) {
        return this.studentRepository
                .findById(studentId)
                .map(this.studentMapper::mapEntityToDto)
                .orElse(null);
    }

    @Override
    public List<StudentDto> allStudentsDto() {
        return this.studentRepository.findAll()
                .stream()
                .map(this.studentMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersDto(Long studentId) {
        StudentEntity student = this.studentRepository.findById(studentId).orElseThrow();
        return student
                .getOrders()
                .stream()
                .map(this.orderMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }


}
