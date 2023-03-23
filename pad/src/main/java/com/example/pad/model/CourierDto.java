package com.example.pad.model;

import com.example.pad.persistence.entity.CourierEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourierDto {
    private Long id;
    private String name;

    public static CourierDto transform(CourierEntity courierEntity) {
        return new CourierDto(courierEntity.getId(), courierEntity.getName());
    }
}
