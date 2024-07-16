package com.ho.jvolt.auth.oauth2.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoApiComponent kakaoApiComponent;

    public String getAccessToken(String code){
        String authorizeCode = "";
        try{
            authorizeCode = kakaoApiComponent.KakaoAccessToken(code);
        }catch (Exception e){
            e.printStackTrace();
        }

        return authorizeCode;
    }

}
