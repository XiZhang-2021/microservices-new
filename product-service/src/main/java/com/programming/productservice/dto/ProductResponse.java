package com.programming.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int id;

    private String name;

    private String author;

    private String description;

    private BigDecimal price;

    private String genre;

    private int stock;

    private String timeversion;

    private String image;
}
