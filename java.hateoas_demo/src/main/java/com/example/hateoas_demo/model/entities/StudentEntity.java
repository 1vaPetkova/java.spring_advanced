package com.example.hateoas_demo.model.entities;

import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class StudentEntity {

    private Long id;
    private String name;
    private int age;
    private List<OrderEntity> orders;


    public StudentEntity() {
        this.orders = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public StudentEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public StudentEntity setAge(int age) {
        this.age = age;
        return this;
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public StudentEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }
}
