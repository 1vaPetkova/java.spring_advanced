package com.example.hateoas_demo.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    private Long id;
    private String name;
    private StudentEntity student;

    public OrderEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public OrderEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderEntity setName(String name) {
        this.name = name;
        return this;
    }

    @ManyToOne
    public StudentEntity getStudent() {
        return student;
    }

    public OrderEntity setStudent(StudentEntity student) {
        this.student = student;
        return this;
    }
}
