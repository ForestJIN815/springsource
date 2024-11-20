package com.example.book.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.dto.PublisherDto;
import com.example.book.entity.Book;
import com.example.book.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/rest")
@RequiredArgsConstructor
@Log4j2
@RestController
public class BookRestController {

    private final BookService bookService;

    // 리스트 추출
    @GetMapping("/list")
    public ResponseEntity<PageResultDto<BookDto, Book>> getList(PageRequestDto requestDto) {
        log.info("도서 전체 목록 요청 {}", requestDto);

        PageResultDto<BookDto, Book> result = bookService.getList(requestDto);
        return new ResponseEntity<PageResultDto<BookDto, Book>>(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> postCreate(BookDto dto) {
        log.info("도서 입력 요청 {}", dto);

        // 서비스 insert 호출
        Long id = bookService.create(dto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> postModify(@PathVariable Long id, BookDto dto) {
        log.info("도서 수정 요청 {}", dto);

        dto.setId(id);
        id = bookService.update(dto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> postMethodName(@PathVariable Long id) {
        log.info("도서 삭제 요청 {} ", id);
        bookService.delete(id);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
