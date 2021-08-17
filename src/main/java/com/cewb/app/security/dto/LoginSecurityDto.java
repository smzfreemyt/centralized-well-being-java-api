package com.cewb.app.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class LoginSecurityDto {

    private Long id;

    @NotEmpty(message = "Email address field is required.")
    @Email(message = "Email address must be a valid email.")
    private String email;

    @NotEmpty(message = "Please enter your password.")
    private String password;
}
