package com.ho.jvolt.auth.oauth2.kakao;

import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoAccessTokenDto;
import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoUserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

@Component
@HttpExchange
public interface KakaoComponent {

    @PostExchange("https://kauth.kakao.com/oauth/token")
    KakaoAccessTokenDto.ApiResponseDto getAccessToken(@RequestParam Map<String, String> body, @RequestHeader Map<String, String> headers);

    @PostExchange("https://kapi.kakao.com/v2/user/me")
    KakaoUserInfo.ApiResponse getUserInfoAll(@RequestHeader Map<String, String> headers);
}
