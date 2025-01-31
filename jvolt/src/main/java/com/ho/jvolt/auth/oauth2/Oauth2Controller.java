package com.ho.jvolt.auth.oauth2;

import com.ho.jvolt.auth.oauth2.kakao.KakaoService;
import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoAccessTokenDto;
import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Oauth2Controller {

    private final KakaoService kakaoService;

    @PostMapping("/login/oauth/code/kakao")
    public ResponseEntity<KakaoAccessTokenDto.Response> kakaoAccessToken(@RequestBody KakaoAccessTokenDto.Request request){

        KakaoAccessTokenDto.Response response = KakaoAccessTokenDto.Response.builder()
                .token(kakaoService.getAccessToken(request.getCode()))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/oauth/kakao")
    public ResponseEntity<KakaoUserInfo.ApiResponse> kakaoLogin(@RequestBody KakaoUserInfo.Request request){
        KakaoUserInfo.ApiResponse userInfo = kakaoService.login(request.getCode());

        return ResponseEntity.ok(userInfo);
    }
}
