package com.ho.jvolt.auth.oauth2.kakao;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange
public interface KakaoComponent {

    @GetExchange("/oauth/authorize")
    String getAuthorizeCode(@RequestParam String response_type, @RequestParam String client_id, @RequestParam String redirect_uri, @RequestHeader String content_type);

}
