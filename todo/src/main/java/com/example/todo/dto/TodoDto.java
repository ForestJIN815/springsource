package com.example.todo.dto;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDto {
    private Long id;

    private Boolean completed;

    private Boolean important;

    private String title;
}
