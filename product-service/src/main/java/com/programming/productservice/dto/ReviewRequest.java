package com.programming.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {
    private int userid;

    private String username;

    private int productid;

    private String productname;

    private String productimage;

    private LocalDateTime reviewtime;

    private String productreview;
}
