package com.example.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDto {
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private String items;

    @Builder
    public OrderDto(String name, String address, String payment, String cardNumber, String items) {
        this.name = name;
        this.address = address;
        this.payment = payment;
        this.cardNumber = cardNumber;
        this.items = items;
    }
}
