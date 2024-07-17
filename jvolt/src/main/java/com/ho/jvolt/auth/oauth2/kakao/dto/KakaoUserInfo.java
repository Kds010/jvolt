package com.ho.jvolt.auth.oauth2.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ho.jvolt.auth.oauth2.kakao.KakaoAccount;
import com.ho.jvolt.auth.oauth2.kakao.KakaoPartner;
import com.ho.jvolt.auth.oauth2.kakao.KakaoUserProperty;
import lombok.*;

import java.time.Instant;

public class KakaoUserInfo {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private String code;
    }

    @ToString
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiResponse{
        private Long id;
        private Boolean has_signed_up;
        private Instant connected_at;
        private Instant synched_at;
        private KakaoUserProperty properties;
        private KakaoAccount kakao_account;
        private KakaoPartner for_partner;
    }

    @ToString
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private String refreshToken;
        private String token;
    }
}
