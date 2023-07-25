package org.programming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeAddr {
    private int userid;
    private String address;
    private String zip;
    private String city;
    private String state;
    private String phone;
}
