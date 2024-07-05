package com.ho.jvolt.auth.dto;

import lombok.*;

public class RegisterDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private String firstname;
        private String lastname;
        private String email;
        private String password;
    }

    @ToString
    @Builder @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Response{
        private boolean success;
        private String message;
        private Integer id;
        private String firstname;
        private String lastname;
        private String email;
    }
}
