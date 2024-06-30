package com.ho.jvolt.common.security.auth.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PasswordResetReqeust {
    private String email;
}
