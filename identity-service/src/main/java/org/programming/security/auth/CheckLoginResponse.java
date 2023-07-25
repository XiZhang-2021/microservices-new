package org.programming.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.programming.bean.User;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckLoginResponse {
    boolean success;
    User user;
}
