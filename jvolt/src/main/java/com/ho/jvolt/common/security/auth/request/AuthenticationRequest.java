package com.ho.jvolt.common.security.auth.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationRequest {
    private String email;
    private String password;
}
