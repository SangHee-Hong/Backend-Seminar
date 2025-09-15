package com.example.seminar.exception.assignment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDTO {
    @NotBlank(message = "아이디는 필수입니다.")
    private String username;

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;
}
