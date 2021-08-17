package com.cewb.app.security.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponse {

    private Long id;

    private String email;

    private String name;

    public RegisterResponse(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
