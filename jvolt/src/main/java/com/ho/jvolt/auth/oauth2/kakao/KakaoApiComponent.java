package com.ho.jvolt.auth.oauth2.kakao;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    public String KakaoAuthCode(){
        JSONObject json = new JSONObject(kakaoComponent.getAuthorizeCode("code", kakaoClientId, redirectUrl, "application/x-www-form-urlencoded;charset=utf-8"));
        return json.getString("token");
    }

}
