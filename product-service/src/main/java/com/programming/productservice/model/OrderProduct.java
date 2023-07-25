package com.programming.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="orderproduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProduct {
    @Id
    @SequenceGenerator(name = "b", sequenceName = "orderproduct_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "b", strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name="ordersid")
    @JsonIgnore
    private Orders orders;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "productid")
    private Product product;

    @Column
    private String image;

    @Column
    private int quantity;

    @Column
    private BigDecimal total;

    @Column
    private BigDecimal price;

    @Column
    private LocalDateTime ordertime;

    @Column
    private String status;

}
