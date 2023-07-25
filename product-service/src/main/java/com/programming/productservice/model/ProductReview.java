package com.programming.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="productreview")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReview {
    @Id
    @SequenceGenerator(name = "f", sequenceName = "productreview_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "f", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private int userid;

    @Column
    private String username;

    @Column
    private int productid;

    @Column
    private String productname;

    @Column
    private String productimage;

    @Column
    private String productreview;

    @Column
    private LocalDateTime reviewtime;
}
