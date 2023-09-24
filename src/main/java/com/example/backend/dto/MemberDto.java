package com.example.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private String email;
    private String password;

    @Builder
    public MemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
