package com.programming.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pendingorderproduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendingOrderProduct {
    @Id
    @SequenceGenerator(name = "e", sequenceName = "pendingorderproduct_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "e", strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name="pendingordersid")
    @JsonIgnore
    private PendingOrders pendingOrders;

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
