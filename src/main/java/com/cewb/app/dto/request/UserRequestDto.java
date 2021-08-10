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

    @NotEmpty
    private String name;

    @NotEmpty
    @Email(message = "Email address must be a valid email.")
    private String email;

    @NotEmpty
    @Size(min = 5, message = "Password must be at least 5 characters.")
    private String password;

    private Long role;
}
