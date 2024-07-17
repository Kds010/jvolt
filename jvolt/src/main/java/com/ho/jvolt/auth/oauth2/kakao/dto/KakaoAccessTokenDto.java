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
        private String grant_type;
        private String code;
        private String client_secret;
    }

    @ToString
    @Builder @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class ApiResponseDto{
        private String access_token;
        private String token_type;
        private String refresh_token;
        private String id_token;
        private int expired_in;
        private String scope;
        private int refresh_token_expired;
    }

    @ToString
    @Builder @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Response{
        private String refreshToken;
        private String token;
    }
}
