package com.example.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemDto {
    private String name;
    private String imgPath;
    private String price;
    private String discountPer;

    @Builder
    public ItemDto(String name, String imgPath, String price, String discountPer) {
        this.name = name;
        this.imgPath = imgPath;
        this.price = price;
        this.discountPer = discountPer;
    }
}
