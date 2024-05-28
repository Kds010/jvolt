package com.ho.jvolt.common.security.auth.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationRequest {
    private String email;
    String password;
}
