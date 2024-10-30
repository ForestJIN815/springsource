package com.example.project2.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Board;
import com.example.project2.entity.Memo;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // C(insert)
    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 20).forEach(i -> {
            Board board = Board.builder()
                    .title("Title...." + i)
                    .content("Content...." + i)
                    .writer("user" + i)
                    .build();
            boardRepository.save(board);
        });
    }

    // R(Read)
    @Test
    public void selectOneTest() {
        System.out.println(boardRepository.findById(6L));
    }

    @Test
    public void selectAllTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    // U
    @Test
    public void updateTest() {
        Board board = boardRepository.findById(5L).get();
        board.setTitle("Update Title");
        board.setContent("Update Content");
        boardRepository.save(board);
    }

    // D
    @Test
    public void deleteTest() {
        boardRepository.deleteById(15L);
    }
}
