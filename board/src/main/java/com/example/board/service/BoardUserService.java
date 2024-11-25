package com.example.board.service;

import com.example.board.dto.MemberDto;
import com.example.board.entity.Member;

public interface BoardUserService {
    // 회원가입
    String register(MemberDto mDto);

    // entity => dto
    default MemberDto entityToDto(Member member) {
        return MemberDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }

    // dto=> entity
    default Member dtoToEntity(MemberDto mDto) {
        return Member.builder()
                .email(mDto.getEmail())
                .name(mDto.getName())
                .password(mDto.getPassword())
                .role(mDto.getRole())
                .build();
    }
}