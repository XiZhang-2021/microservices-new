package com.programming.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "favoriate")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favoriate {
    @Id
    @SequenceGenerator(name = "f", sequenceName = "favoriate_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "f", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private int userid;

    @Column
    private String username;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name="productid")
    private Product product;

    @Column
    private String productname;

    @Column
    private BigDecimal productprice;

    @Column
    private String description;

    @Column
    private String author;

    @Column
    private String image;
}
