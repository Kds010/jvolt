package com.ho.jvolt.auth.oauth2.kakao;

import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoAccessTokenDto;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@Component
@HttpExchange
public interface KakaoComponent {

    @PostExchange("/oauth/token")
    JSONObject getAccessToken(@RequestBody KakaoAccessTokenDto.ApiRequestDto body, @RequestHeader String content_type);

}
