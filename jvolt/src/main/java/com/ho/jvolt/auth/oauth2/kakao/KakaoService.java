package com.ho.jvolt.auth.oauth2.kakao;

import com.ho.jvolt.auth.oauth2.kakao.dto.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoApiComponent kakaoApiComponent;

    public String getAccessToken(String code){
        String authorizeCode = "";
        try{
            authorizeCode = kakaoApiComponent.getAccessToken(code);
        }catch (Exception e){
            e.printStackTrace();
        }

        return authorizeCode;
    }

    public KakaoUserInfo.ApiResponse login(String code){
        String accessToken = kakaoApiComponent.getAccessToken(code);
        KakaoUserInfo.ApiResponse userInfo = kakaoApiComponent.getUserInfo(accessToken);
        return userInfo;
    }

}
