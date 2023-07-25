package com.programming.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    @Id
    @SequenceGenerator(name = "c", sequenceName = "orders_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "c", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private int userid;

    @Column
    private String username;

    @Column
    private BigDecimal totalprice;

    @Column
    private LocalDateTime ordertime;

    @Column
    private String orderaddress;

    @Column
    private String orderpaymentinfo;//the last 4 digits of the cardnumber

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "orders")
    private List<OrderProduct> purchases;

    @Column
    private String status;

}
