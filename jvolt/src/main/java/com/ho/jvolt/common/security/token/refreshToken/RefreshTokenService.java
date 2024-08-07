package com.ho.jvolt.common.security.token.refreshToken;

import com.ho.jvolt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshToken createRefreshToken(String userEmail){
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByEmail(userEmail).orElseThrow((() -> new UsernameNotFoundException("User not found"))))
                .token(UUID.randomUUID().toString())
//                .expiryDate(Instant.now().plusMillis( 60 * 1000)) // 1분.
                .expiryDate(Instant.now().plusMillis( 10 * 60 * 1000)) // 10분.
//                .expiryDate(Instant.now().plusMillis(3 * 30 * 24 * 60 * 60 * 1000)) // 3개월 설정. TODO 만료시간 config 파일에 명시로 수정
                .build();
        /*
            단기 유효 기간: 1주일에서 1개월 (보안이 중요한 시스템에서 사용)
            중기 유효 기간: 3개월에서 6개월 (일반적인 웹 애플리케이션에서 많이 사용)
            장기 유효 기간: 6개월에서 1년 (보안 요구 사항이 낮은 시스템에서 사용)
         */
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token has expired. Please log in again");
        }
        return token;
    }
}
