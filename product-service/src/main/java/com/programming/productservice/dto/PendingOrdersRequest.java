package com.programming.productservice.dto;

import com.programming.productservice.model.OrderProduct;
import com.programming.productservice.model.PendingOrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PendingOrdersRequest {
    private int userid;

    private String username;

    private BigDecimal totalprice;

    private String orderaddress;

    private String orderpaymentinfo;//the last 4 digits of the cardnumber

    private List<PendingOrderProduct> purchases;


}

