package com.ho.jvolt.common.security.auth;

import com.ho.jvolt.common.security.auth.request.AuthenticationRequest;
import com.ho.jvolt.common.security.auth.request.RegisterRequest;
import com.ho.jvolt.common.security.auth.response.AuthenticationResponse;
import com.ho.jvolt.common.security.auth.response.RegisterResponse;
import com.ho.jvolt.common.security.config.JwtService;
import com.ho.jvolt.common.security.token.refreshToken.RefreshToken;
import com.ho.jvolt.common.security.token.refreshToken.RefreshTokenService;
import com.ho.jvolt.user.Role;
import com.ho.jvolt.user.User;
import com.ho.jvolt.user.UserRepository;
import com.ho.jvolt.user.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public RegisterResponse register(RegisterRequest request) throws Exception {

        Optional<User> userYN = userRepository.findByEmail(request.getEmail());
        if(userYN.isPresent()){
            throw new Exception("User with email "+request.getEmail() + " already exists.");
        }

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return UserMapper.INSTANCE.userToRegisterResponse(user, true, "회원가입이 성공적으로 완료되었습니다.");
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .refreshToken(refreshToken.getToken())
                .token(jwtToken)
                .build();
    }
}
