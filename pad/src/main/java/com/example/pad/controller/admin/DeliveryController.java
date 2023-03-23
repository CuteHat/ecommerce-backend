package com.example.pad.controller.admin;

import com.example.pad.model.DeliveryDto;
import com.example.pad.persistence.model.DeliveryStatus;
import com.example.pad.service.DeliveryServiceFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/delivery")
@RequiredArgsConstructor
@Validated
public class DeliveryController {
    private final DeliveryServiceFacade deliveryServiceFacade;

    @GetMapping("/filter")
    public ResponseEntity<List<DeliveryDto>> filter(@RequestParam(required = false) DeliveryStatus status,
                                                    @RequestParam(required = false) BigDecimal totalPriceFrom,
                                                    @RequestParam(required = false) BigDecimal totalPriceTo,
                                                    @RequestParam(required = false) Timestamp timeFrom,
                                                    @RequestParam(required = false) Timestamp timeTo) {
        return ResponseEntity.ok(deliveryServiceFacade.filter(status, totalPriceFrom, totalPriceTo, timeFrom, timeTo));
    }

    @PostMapping("/{id}/status")
    public DeliveryDto updateStatus(@PathVariable @Min(1) Long id, @RequestParam DeliveryStatus status) {
        return deliveryServiceFacade.updateStatus(id, status);
    }
}
