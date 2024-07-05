package com.ho.jvolt.auth.dto;

import lombok.*;

public class AuthenticationDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private String email;
        private String password;
    }

    @ToString
    @Builder @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Response{
        private String refreshToken;
        private String token;
    }
}
