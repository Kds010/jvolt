package com.ho.jvolt.common.security.auth.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
