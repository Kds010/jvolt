package com.ho.jvolt.common.security.auth;

import com.ho.jvolt.common.security.auth.request.AuthenticationRequest;
import com.ho.jvolt.common.security.auth.request.RegisterRequest;
import com.ho.jvolt.common.security.auth.response.AuthenticationResponse;
import com.ho.jvolt.common.security.config.JwtService;
import com.ho.jvolt.common.security.token.refreshToken.RefreshToken;
import com.ho.jvolt.common.security.token.refreshToken.RefreshTokenService;
import com.ho.jvolt.common.security.token.refreshToken.request.RefreshTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
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
}