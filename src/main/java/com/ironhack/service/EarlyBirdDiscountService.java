package com.ironhack.service;

import com.ironhack.dto.DiscountResponse;
import com.ironhack.exception.BusinessException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EarlyBirdDiscountService {
    public DiscountResponse getDiscount(LocalDate eventDate, LocalDate bookingDate) {
        // Bookings made at least 60 days in advance receive a 20% discount
        // Bookings made at least 30 days in advance receive a 15% discount
        // Bookings made between 15 and 29 days in advance receive a 10% discount

        long daysInAdvance = ChronoUnit.DAYS.between(bookingDate, eventDate);

        if (daysInAdvance < 0) {
            throw new BusinessException("Booking date cannot be after the event date.");
        }
        double discountPercentage = getDiscountPercentage(daysInAdvance);

        return new DiscountResponse(
            discountPercentage,
            "Early Bird Discount, Book early and save up to 20% on your event tickets!"
        );
    }

    private double getDiscountPercentage(long daysInAdvance) {
        if (daysInAdvance >= 60) {
            return 20.0;
        } else if (daysInAdvance >= 30) {
            return 15.0;
        } else if (daysInAdvance >= 15) {
            return 10.0;
        } else {
            return 0.0;
        }
    }
}
