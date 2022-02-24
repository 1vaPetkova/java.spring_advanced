package com.example.hateoas_demo.model.mapping;


import com.example.hateoas_demo.model.dto.OrderDto;
import com.example.hateoas_demo.model.entities.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    default OrderDto mapEntityToDTO(OrderEntity order) {

        if (order == null) {
            return null;
        }

        return new OrderDto()
                .setId(order.getId())
                .setStudentId(order.getStudent().getId())
                .setCourseId(order.getCourse().getId());
    }
}
