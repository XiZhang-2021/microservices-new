package com.programming.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;


    private String username;


    private String password;


    private String address;


    private String phone;


    private String city;


    private String state;


    private String validateexpdate;


    private String zip;


    private String cardnumber;


    private String cvv;


    private Profile profile;

}
