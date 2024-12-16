package com.example.movie.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsert() {

        // IntStream.rangeClosed(1, 50).forEach(i -> {
        Member member = Member.builder()
                .email("admin@naver.com")
                .password(passwordEncoder.encode("1111"))
                .nickname("admin")
                .role(MemberRole.ADMIN)
                .build();

        memberRepository.save(member);
        // });
    }

    @Test
    public void testUpdate() {

        Member member = memberRepository.findById(2L).get();
        member.setNickname("flower");
        memberRepository.save(member);
    }

    @Transactional
    @Test
    public void testUpdate2() {

        memberRepository.updateNickName("test", "user3@naver.com");
    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {
        // 리뷰삭제(리뷰를 작성한 멤버를 이용해서 삭제)
        reviewRepository.deleteByMember(Member.builder().mid(50L).build());
        // 회원삭제
        memberRepository.deleteById(50L);
    }
}