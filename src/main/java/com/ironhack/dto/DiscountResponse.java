package com.ironhack.dto;

public record DiscountResponse(
        Double discountPercentage,
        String message
) {}
