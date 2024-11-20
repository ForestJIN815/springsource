package com.example.project1.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.project1.dto.SampleDto2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// 컨트롤러에 어노테이션 추가
// 1) @Controller : view(html, jsp,...) 를 찾으러 감
// 2) @RestController : 리턴하는 값 자체가 브라우저에 보여짐
//     - 객체 리턴 가능 : 객체는 브라우저에 띄울 수 없음
//      ==> converter 가 필요함(spring boot 가 자동으로 라이브러리를 가지고 있음)
//          객체 <===> json

@RestController
public class TestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hollo World";
    }

    @GetMapping("/sample1")
    public SampleDto2 getSample1() {
        return SampleDto2.builder()
                .mno(11L)
                .firstName("hong")
                .lastName("dong")
                .build();
    }

    @GetMapping("/sample2")
    public List<SampleDto2> getSample2() {

        List<SampleDto2> list = new ArrayList<>();
        LongStream.rangeClosed(1, 10).forEach(i -> {
            SampleDto2 sampleDto2 = SampleDto2.builder()
                    .mno(i)
                    .firstName("hong")
                    .lastName("dong" + i)
                    .build();

            list.add(sampleDto2);
        });

        return list;
    }

    // http://localhost:8080/check?height=180&weight=80
    @GetMapping("/check")
    public ResponseEntity<SampleDto2> check(double height, double weight) {
        SampleDto2 sampleDto2 = SampleDto2.builder()
                .mno(11L)
                .firstName(String.valueOf(height))
                .lastName(String.valueOf(weight))
                .build();

        if (height < 160) {
            return new ResponseEntity<SampleDto2>(sampleDto2, HttpStatusCode.valueOf(500));
        } else {
            // 200
            return new ResponseEntity<SampleDto2>(sampleDto2, HttpStatus.OK);
        }
    }

    // @PathVariable

    // http://localhost:8080/products/bag/1234
    // http://localhost:8080/products/shirt/4567

    @GetMapping("/products/{cat}/{pid}")
    public String[] getMethodName(@PathVariable String cat, @PathVariable String pid) {
        return new String[] {
                "category : " + cat,
                "productId : " + pid
        };
    }
}