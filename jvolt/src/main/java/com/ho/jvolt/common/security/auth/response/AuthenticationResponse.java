package com.ho.jvolt.common.security.auth.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationResponse {
    private String refreshToken;
    private String token;
}
