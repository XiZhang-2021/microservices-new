package com.programming.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.programming.productservice.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart implements Serializable {
    @Id
    @SequenceGenerator(name = "a", sequenceName = "cart_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "a", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private int userid;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE, mappedBy = "cart")
    @JsonIgnore
    private List<CartProduct> cartProductList;

}
