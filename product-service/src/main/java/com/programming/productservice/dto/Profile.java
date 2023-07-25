package com.programming.productservice.dto;

import jakarta.persistence.Column;

import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile  {

    private int id;

    private String type;

}
