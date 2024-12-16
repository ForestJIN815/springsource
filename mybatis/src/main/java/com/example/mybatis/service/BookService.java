package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.dto.BookDto;
import com.example.mybatis.dto.CategoryDto;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PageResultDto;
import com.example.mybatis.dto.PublisherDto;

public interface BookService {

    // crud
    boolean create(BookDto dto);

    BookDto getRow(Long id);

    List<BookDto> getList(PageRequestDto requestDto);

    int getTotalCnt(PageRequestDto requestDto);

    boolean update(BookDto dto);

    boolean delete(Long id);

    List<CategoryDto> getCateList();

    List<PublisherDto> getPubList();

}
