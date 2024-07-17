package com.ho.jvolt.user;

import com.ho.jvolt.common.redis.RedisService;
import com.ho.jvolt.common.smtp.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private final UserRepository userRepository;
    private final MailService mailService;
    private final RedisService redisService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    public void sendCodeToEmail(String toEmail) {
        this.checkDuplicatedEmail(toEmail);
        String title = "Travel with me 이메일 인증 번호";
        String authCode = this.createCode();
        mailService.sendSimpleMail(toEmail, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
                authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    private void checkDuplicatedEmail(String email) {
        Optional<User> member = userRepository.findByEmail(email);
        if (member.isPresent()) {
            logger.debug("MemberServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
            throw new RuntimeException();
        }
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.debug("MemberService.createCode() exception occur");
            throw new RuntimeException();
        }
    }

    public Boolean verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);
        boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);

        return authResult;
    }
}
