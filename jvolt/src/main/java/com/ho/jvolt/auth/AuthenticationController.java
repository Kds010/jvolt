package com.ho.jvolt.auth;

import com.ho.jvolt.auth.dto.AuthenticationDto;
import com.ho.jvolt.auth.dto.PasswordResetDto;
import com.ho.jvolt.auth.dto.RegisterDto;
import com.ho.jvolt.common.security.config.JwtService;
import com.ho.jvolt.common.security.token.refreshToken.RefreshToken;
import com.ho.jvolt.common.security.token.refreshToken.RefreshTokenService;
import com.ho.jvolt.common.security.token.refreshToken.dto.RefreshTokenDto;
import com.ho.jvolt.common.smtp.MailService;
import com.ho.jvolt.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final MailService mailService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterDto.Response> register(
            @RequestBody RegisterDto.Request request
    ) throws Exception {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationDto.Response> register(
            @RequestBody AuthenticationDto.Request request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationDto.Response> refreshToken(@RequestBody RefreshTokenDto.Request refreshTokenRequest){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(refreshTokenRequest.getToken());
        if (optionalRefreshToken.isEmpty()) {
            throw new RuntimeException("Refresh Token is not in DB");
        }

        RefreshToken refreshToken = optionalRefreshToken.get();
        refreshTokenService.verifyExpiration(refreshToken);
        UserDetails userDetails = refreshToken.getUser();

        String newToken = jwtService.generateToken(userDetails);
        AuthenticationDto.Response response = AuthenticationDto.Response.builder()
                .token(newToken)
                .refreshToken(refreshTokenRequest.getToken())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verifyPasswordReset")
    public ResponseEntity verifyPasswordReset(
            @RequestBody PasswordResetDto.Request passwordResetReqeust
            ) {
//        mailService.sendSimpleMail(passwordResetReqeust.getEmail(), );
        userService.sendCodeToEmail(passwordResetReqeust.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verifyPasswordResetConfirm")
    public ResponseEntity verifyPasswordResetConfirm(
            @RequestParam("email") String email,
            @RequestParam("code") String authCode
    ) {
        Boolean response = userService.verifiedCode(email, authCode);
        if(response){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        return new ResponseEntity<>(HttpStatus.OK);
    }
}
