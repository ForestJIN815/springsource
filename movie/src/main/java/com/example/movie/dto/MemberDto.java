package com.example.movie.dto;

import java.time.LocalDateTime;

import com.example.movie.entity.constant.MemberRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
    private Long mid;

    @Email
    @NotBlank(message = "이메일은 필수 입력요소입니다")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력요소입니다")
    private String password;
    @NotBlank(message = "닉네임은 필수 입력요소입니다")
    private String nickname;

    private MemberRole role;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
