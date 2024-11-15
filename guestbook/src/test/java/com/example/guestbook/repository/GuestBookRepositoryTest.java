package com.example.guestbook.repository;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class GuestBookRepositoryTest {

    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                    .title("Title.." + i)
                    .content("Content..." + i)
                    .writer("user" + i)
                    .build();
            guestBookRepository.save(guestBook);
        });
    }

    @Test
    public void testUpdate() {
        // 300번 수정
        GuestBook guestBook = guestBookRepository.findById(300L).get();

        guestBook.setTitle("Changed title..");
        guestBook.setContent("Changed content..");

        guestBookRepository.save(guestBook);
    }

    // R
    @Test
    public void testSearch() {
        // 제목에 1 이라는 글자가 들어있는 게시물 조회
        QGuestBook qGuestBook = QGuestBook.guestBook;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qGuestBook.title.contains("1"));

        // Predicate predicate(BooleanBuilder 사용), Pageable pageable
        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> System.out.println(guestbook));
    }

    @Test
    public void testSearch2() {
        // 제목 or 내용에 1 이라는 글자가 들어있고 gno > 0 게시물 조회

        QGuestBook qGuestBook = QGuestBook.guestBook;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expressionTitle = qGuestBook.title.contains(keyword);
        BooleanExpression expressionContent = qGuestBook.content.contains(keyword);

        builder.and(expressionTitle.or(expressionContent));
        builder.and(qGuestBook.gno.gt(0L));

        // Predicate predicate(BooleanBuilder 사용), Pageable pageable
        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> System.out.println(guestbook));
    }

    @Test
    public void testSearch3() {
        // 제목 or 내용에 1 이라는 글자가 들어있고 gno > 0 게시물 조회

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        // Predicate predicate(BooleanBuilder 사용), Pageable pageable
        Page<GuestBook> result = guestBookRepository.findAll(guestBookRepository.makePredicate("tc", "Title"),
                pageable);

        result.stream().forEach(guestbook -> System.out.println(guestbook));
    }

    @Test
    public void testDelete() {
        guestBookRepository.deleteById(250L);
    }
}
