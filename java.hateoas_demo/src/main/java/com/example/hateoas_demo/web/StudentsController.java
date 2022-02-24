package com.example.hateoas_demo.web;


import com.example.hateoas_demo.model.dto.OrderDto;
import com.example.hateoas_demo.model.dto.StudentDto;
import com.example.hateoas_demo.model.entities.StudentEntity;
import com.example.hateoas_demo.services.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<StudentDto>>> getStudents() {
        List<EntityModel<StudentDto>> allStudents = this.studentService
                .allStudentsDto()
                .stream()
                .map(dto -> EntityModel.of(dto, createStudentLinks(dto)))
                .toList();
        return ResponseEntity.ok(CollectionModel.of(allStudents));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDto>>> getOrders(@PathVariable("id") Long studentId) {
        List<OrderDto> ordersDto = this.studentService.getOrdersDto(studentId);
        List<EntityModel<OrderDto>> orders = ordersDto
                .stream()
                .map(EntityModel::of).toList();
        return ResponseEntity.ok(CollectionModel.of(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDto>> getStudentsById(@PathVariable("id") Long studentId) {
        StudentDto student = this.studentService.findById(studentId);//todo

        return ResponseEntity.ok(EntityModel.of(student, createStudentLinks(student)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDto>> update(@PathVariable("id") Long id, StudentDto studentDto) {


        return ResponseEntity.ok().build();
    }

    private Link[] createStudentLinks(StudentDto studentDto) {
        List<Link> result = new ArrayList<>();
        Link selfLink = linkTo(methodOn(StudentsController.class)
                .getStudentsById(studentDto.getId()))
                .withSelfRel();

        result.add(selfLink);

        Link updateLink = linkTo(methodOn(StudentsController.class)
                .update(studentDto.getId(), studentDto))
                .withRel("update");
        result.add(updateLink);

        Link orderLink = linkTo(methodOn(StudentsController.class)
                .getOrders(studentDto.getId()))
                .withRel("orders");

        result.add(orderLink);

        return result.toArray(new Link[0]);
    }
}
