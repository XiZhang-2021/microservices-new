package com.programming.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartAddProductRequest {
    private int productid;

    private int userid;

    private String image;

    private String name;

    private String description;

    private int quantity;

    private BigDecimal price;

    private BigDecimal total;
}
