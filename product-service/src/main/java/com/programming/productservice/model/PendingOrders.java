package com.programming.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pendingorders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendingOrders {
    @Id
    @SequenceGenerator(name = "d", sequenceName = "pendingorders_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "d", strategy = GenerationType.SEQUENCE)
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

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pendingOrders")
    private List<PendingOrderProduct> purchases;

    @Column
    private String status;

}
