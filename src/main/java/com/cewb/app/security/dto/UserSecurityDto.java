package com.cewb.app.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSecurityDto {

    private Long id;

    private String email;

    private String password;

    private String name;
}
