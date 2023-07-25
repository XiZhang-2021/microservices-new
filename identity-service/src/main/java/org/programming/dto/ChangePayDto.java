package org.programming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePayDto {
    private int userid;
    private String cardnumber;
    private String expiredate;
    private String cvv;
}
