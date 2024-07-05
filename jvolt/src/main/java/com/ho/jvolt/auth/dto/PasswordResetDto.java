package com.ho.jvolt.auth.dto;

import lombok.*;

public class PasswordResetDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private String email;
    }
}
