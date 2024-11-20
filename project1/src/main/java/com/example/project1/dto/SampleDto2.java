package com.example.project1.dto;

import groovy.transform.ToString;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@ToString
public class SampleDto2 {
    private Long mno;
    private String firstName;
    private String lastName;
}
