package com.ho.jvolt.common.security.auth.response;

import com.ho.jvolt.user.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterResponse {
    private boolean success;
    private String message;
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
}
