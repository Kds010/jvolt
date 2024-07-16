package com.ho.jvolt.auth.oauth2.kakao;

import ch.qos.logback.classic.Logger;
import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoAccessTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoApiComponent {

    @Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${kakao.client_secret}")
    private String kakaoClientSecret;

    @Value("${kakao.redirect_url}")
    private String redirectUrl;

    private final KakaoComponent kakaoComponent;

    public String KakaoAccessToken(String code){

        KakaoAccessTokenDto.ApiRequestDto body = KakaoAccessTokenDto.ApiRequestDto.builder()
                .grant_type("authorization_code")
                .client_id(kakaoClientId)
                .redirect_uri(redirectUrl)
                .code(code)
                .client_secret(kakaoClientSecret)
                .build();

        JSONObject response = kakaoComponent.getAccessToken(body, "application/x-www-form-urlencoded;charset=utf-8");
        log.info(response.toString());
        return "";
    }

}
