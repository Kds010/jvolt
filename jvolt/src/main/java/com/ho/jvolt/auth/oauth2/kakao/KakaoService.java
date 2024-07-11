package com.ho.jvolt.auth.oauth2.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoApiComponent kakaoApiComponent;

    // 인가 코드 받는 용도
    public String getAuthorizeCode(){
        String authorizeCode = "";
        try{
            authorizeCode = kakaoApiComponent.KakaoAuthCode();
        }catch (Exception e){
            e.printStackTrace();
        }

        return authorizeCode;
    }

}
