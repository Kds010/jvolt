package com.ho.jvolt.auth.oauth2.kakao;

import lombok.*;

public class KakaoAuthCodeDto {

    @ToString
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private String token;
    }
}
