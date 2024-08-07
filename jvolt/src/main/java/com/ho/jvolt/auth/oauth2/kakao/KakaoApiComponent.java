package com.ho.jvolt.auth.oauth2.kakao;

import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoAccessTokenDto;
import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KakaoApiComponent {

    private final Logger logger = LoggerFactory.getLogger(KakaoApiComponent.class);

    @Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${kakao.client_secret}")
    private String kakaoClientSecret;

    @Value("${kakao.redirect_url}")
    private String redirectUrl;

    private final KakaoComponent kakaoComponent;

    public String getAccessToken(String code){

        KakaoAccessTokenDto.ApiRequestDto abody = KakaoAccessTokenDto.ApiRequestDto.builder()
                .grant_type("authorization_code")
                .client_id(kakaoClientId)
                .redirect_uri(redirectUrl)
                .code(code)
                .client_secret(kakaoClientSecret)
                .build();

        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "authorization_code");
        body.put("client_id", kakaoClientId);
        body.put("redirect_uri", redirectUrl);
        body.put("code", code);
        body.put("client_secret", kakaoClientSecret);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        KakaoAccessTokenDto.ApiResponseDto response = kakaoComponent.getAccessToken(body, headers);

        logger.info(response.toString());

        return response.getAccess_token();
    }

    public KakaoUserInfo.ApiResponse getUserInfo(String accessToken){

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        KakaoUserInfo.ApiResponse response = kakaoComponent.getUserInfoAll(headers);

        logger.info(response.toString());
//        logger.info(response.isEmpty() ? "" : response);

        return response;
    }

}
