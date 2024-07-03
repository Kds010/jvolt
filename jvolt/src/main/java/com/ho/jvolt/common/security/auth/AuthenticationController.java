package com.ho.jvolt.common.security.auth;

import com.ho.jvolt.common.security.auth.request.AuthenticationRequest;
import com.ho.jvolt.common.security.auth.request.PasswordResetReqeust;
import com.ho.jvolt.common.security.auth.request.RegisterRequest;
import com.ho.jvolt.common.security.auth.response.AuthenticationResponse;
import com.ho.jvolt.common.security.auth.response.RegisterResponse;
import com.ho.jvolt.common.security.config.JwtService;
import com.ho.jvolt.common.security.token.refreshToken.RefreshToken;
import com.ho.jvolt.common.security.token.refreshToken.RefreshTokenService;
import com.ho.jvolt.common.security.token.refreshToken.request.RefreshTokenRequest;
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
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
    ) throws Exception {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(refreshTokenRequest.getToken());
        if (optionalRefreshToken.isEmpty()) {
            throw new RuntimeException("Refresh Token is not in DB");
        }

        RefreshToken refreshToken = optionalRefreshToken.get();
        refreshTokenService.verifyExpiration(refreshToken);
        UserDetails userDetails = refreshToken.getUser();

        String newToken = jwtService.generateToken(userDetails);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(newToken)
                .refreshToken(refreshTokenRequest.getToken())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verifyPasswordReset")
    public ResponseEntity verifyPasswordReset(
            @RequestBody PasswordResetReqeust passwordResetReqeust
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
