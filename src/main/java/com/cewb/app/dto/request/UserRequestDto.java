package com.cewb.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Email address must not be empty")
    @Email(message = "Email address must be a valid email.")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 5, message = "Password must be at least 5 characters.")
    private String password;

    private String role;
}
