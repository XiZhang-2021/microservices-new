package com.programming.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    @SequenceGenerator(name = "z", sequenceName = "product_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "z", strategy = GenerationType.SEQUENCE)
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
