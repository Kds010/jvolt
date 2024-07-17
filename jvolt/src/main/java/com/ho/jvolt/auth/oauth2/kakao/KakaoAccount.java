package com.ho.jvolt.auth.oauth2.kakao;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KakaoAccount {

    private Boolean profile_needs_agreement;	// 사용자 동의 시 프로필 정보(닉네임/프로필 사진) 제공 가능
    private Boolean profile_nickname_needs_agreement; // 사용자 동의 시 닉네임 제공 가능
    private Boolean profile_image_needs_agreement; //	사용자 동의 시 프로필 사진 제공 가능
    private KakaoProfile profile;	//	프로필 정보
    private Boolean name_needs_agreement; //	사용자 동의 시 카카오계정 이름 제공 가능
    private String name;	//	카카오계정 이름
    private Boolean email_needs_agreement;	//	사용자 동의 시 카카오계정 대표 이메일 제공 가능
    private Boolean is_email_valid;	//	이메일 유효 여부, true: 유효한 이메일, false: 이메일이 다른 카카오계정에 사용돼 만료
    private Boolean is_email_verified;	//	이메일 인증 여부, true: 인증된 이메일, false: 인증되지 않은 이메일
    private String email;	//	카카오계정 대표 이메일
    private Boolean age_range_needs_agreement;	//	사용자 동의 시 연령대 제공 가능
    private String age_range; //		연령대
    private Boolean birthyear_needs_agreement;	//	사용자 동의 시 출생 연도 제공 가능
    private String birthyear; //	 	출생 연도(YYYY 형식)
    private Boolean birthday_needs_agreement; //		사용자 동의 시 생일 제공 가능
    private String birthday; //		생일(MMDD 형식)
    private String birthday_type; //		생일 타입, SOLAR(양력) 또는 LUNAR(음력)
    private Boolean gender_needs_agreement; //		사용자 동의 시 성별 제공 가능
    private String gender; //		성별, female: 여성, male: 남성
    private Boolean phone_number_needs_agreement; //		사용자 동의 시 전화번호 제공 가능
    private String phone_number; //		카카오계정의 전화번호
                                 // 국내 번호인 경우 +82 00-0000-0000 형식
                                 // 해외 번호인 경우 자릿수, 붙임표(-) 유무나 위치가 다를 수 있음
    private Boolean ci_needs_agreement; //		사용자 동의 시 CI 참고 가능
    private String ci; //		연계정보
    private LocalDateTime ci_authenticated_at; //		CI 발급 시각, UTC*
}
