package com.cewb.app.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class AuthSecurityDto {

    private Long id;

    @NotEmpty
    @Email(message = "Email address must be a valid email.")
    private String email;

    @NotEmpty
    @Min(value = 5, message = "Password must be at least 5 characters")
    private String password;

    @NotEmpty
    private String name;
}
