package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.dto.PublisherDto;
import com.example.book.entity.Book;
import com.example.book.service.BookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/book")
@Controller
public class BookController {

    private final BookService bookService;

    // 리스트 추출
    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("도서 전체 목록 요청 {}", requestDto);

        PageResultDto<BookDto, Book> result = bookService.getList(requestDto);
        model.addAttribute("result", result);
    }

    // 상세조회
    @GetMapping(value = { "/read", "/modify" })
    public void getMethodName(@RequestParam Long id, @ModelAttribute("requestDto") PageRequestDto requestDto,
            Model model) {
        log.info("도서 상세 요청 {}", id);

        BookDto dto = bookService.getRow(id);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postModify(BookDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("도서 수정 요청 {}", dto);
        log.info("requestDto {} ", requestDto);

        Long id = bookService.update(dto);

        // 상세조회로 이동
        rttr.addAttribute("id", id); // ?id=3
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:read";
    }

    @PostMapping("/remove")
    public String postMethodName(@RequestParam Long id, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("도서 삭제 요청 {} ", id);
        log.info("requestDto {} ", requestDto);

        bookService.delete(id);

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:list";
    }

    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BookDto dto, Model model,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("도서 입력 폼 요청");

        List<CategoryDto> categories = bookService.getCateList();
        List<PublisherDto> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publisherDtos);
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BookDto dto, BindingResult result, Model model,
            RedirectAttributes rttr) {
        log.info("도서 입력 요청 {}", dto);

        List<CategoryDto> categories = bookService.getCateList();
        List<PublisherDto> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publisherDtos);

        if (result.hasErrors()) {
            return "/book/create";
        }

        // 서비스 insert 호출
        Long id = bookService.create(dto);

        rttr.addFlashAttribute("msg", id + "번 도서가 등록되었습니다.");
        return "redirect:list";
    }

}
