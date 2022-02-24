package com.example.hateoas_demo.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "courses")
public class CourseEntity {

    private Long id;
    private String name;
    private BigDecimal price;
    private boolean enabled;
    private List<OrderEntity> orders;

    public CourseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public CourseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CourseEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public CourseEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public CourseEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }
}
