package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    // 닉네임 수정
    @Modifying
    @Query("UPDATE Member m SET m.nickname=:nickname WHERE m.email=:email")
    void updateNickName(String nickname, String email);
}