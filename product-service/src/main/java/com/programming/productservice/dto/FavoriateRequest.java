package com.programming.productservice.dto;

import com.programming.productservice.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriateRequest {

    private int userid;

    private String username;

    private Product product;

    private String productname;

    private BigDecimal productprice;

    private String description;

    private String author;

    private String image;
}
