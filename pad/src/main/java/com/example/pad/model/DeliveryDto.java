package com.example.pad.model;

import com.example.pad.persistence.entity.DeliveryEntity;
import com.example.pad.persistence.model.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryDto {
    private Long id;
    private DeliveryStatus status;
    private CourierDto courier;
    private OrderDto order;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public static DeliveryDto transform(DeliveryEntity deliveryEntity) {
        return new DeliveryDto(
                deliveryEntity.getId(),
                deliveryEntity.getStatus(),
                CourierDto.transform(deliveryEntity.getCourier()),
                OrderDto.transform(deliveryEntity.getOrder()),
                deliveryEntity.getCreatedAt(),
                deliveryEntity.getUpdatedAt()
        );
    }
}
