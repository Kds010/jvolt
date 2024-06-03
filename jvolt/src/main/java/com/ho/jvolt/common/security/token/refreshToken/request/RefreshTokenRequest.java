package com.ho.jvolt.common.security.token.refreshToken.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RefreshTokenRequest {
    private String token;
}
