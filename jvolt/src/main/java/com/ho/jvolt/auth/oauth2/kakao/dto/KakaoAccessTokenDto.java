package com.ho.jvolt.auth.oauth2.kakao.dto;

import lombok.*;

public class KakaoAccessTokenDto {

    @ToString
    @Builder @Getter
    @AllArgsConstructor @NoArgsConstructor
    public static class Request{
        private String code;
    }

    @ToString
    @Builder @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class ApiRequestDto{
        private String client_id;
        private String redirect_uri;
        private String grant_type = "authorization_code";
        private String code;
        private String client_secret;
    }

    @ToString
    @Builder @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Response{
        private String refreshToken;
        private String token;
    }
}
