package org.programming.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;

    private String password;

    private String type;

    private String address;

    private String phone;

    private String city;

    private String state;

    private String zip;

    private String cardnumber;

    private String validateexpdate;

    private String cvv;
}
