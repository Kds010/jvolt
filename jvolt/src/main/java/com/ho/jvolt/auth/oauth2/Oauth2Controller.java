package com.ho.jvolt.auth.oauth2;

import com.ho.jvolt.auth.oauth2.kakao.KakaoAuthCodeDto;
import com.ho.jvolt.auth.oauth2.kakao.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Oauth2Controller {

    private final KakaoService kakaoService;

    @RequestMapping("/login/oauth/code/kakao")
    public ResponseEntity<KakaoAuthCodeDto.Response> kakaoLoginCode(@RequestParam String code){

        KakaoAuthCodeDto.Response response = KakaoAuthCodeDto.Response.builder()
                .token(kakaoService.getAuthorizeCode())
                .build();

        return ResponseEntity.ok(response);
    }
}
