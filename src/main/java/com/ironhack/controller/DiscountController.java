package com.ironhack.controller;

import com.ironhack.dto.DiscountResponse;
import com.ironhack.exception.BusinessException;
import com.ironhack.service.EarlyBirdDiscountService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class DiscountController {
    private final EarlyBirdDiscountService earlyBirdDiscountService;

    public DiscountController(Optional<EarlyBirdDiscountService> earlyBirdDiscountService) {
        this.earlyBirdDiscountService = earlyBirdDiscountService.orElse(null);
    }

    @GetMapping("/api/discount")
    public ResponseEntity<DiscountResponse> getDiscount(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate eventDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate bookingDate
    ) {
        checkEarlyBirdDiscountService();
        return ResponseEntity.ok(earlyBirdDiscountService.getDiscount(eventDate, bookingDate));
    }

    private void checkEarlyBirdDiscountService() {
        if (earlyBirdDiscountService == null) {
            throw new BusinessException("Early Bird Discount is not available!");
        }
    }
}
