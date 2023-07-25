package com.programming.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="cartproduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProduct {
    @Id
    @SequenceGenerator(name = "b", sequenceName = "cartproduct_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "b", strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "cartid")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "productid")
    private Product product;

    private int quantity;

    private String description;

    private String image;

    private String name;

    private BigDecimal price;

    private BigDecimal total;
}
