package com.example.todo;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    Todo todo = Todo.builder()
                            .title("할 일" + i)
                            .build();

                    todoRepository.save(todo);
                });

    }

    @Test
    public void selectAllTest() {
        todoRepository.findAll().forEach(todo -> System.out.println(todo));
    }

    @Test
    public void selectOneTest() {
        System.out.println(todoRepository.findById(3L).get());

    }

    @Test
    public void updateTest() {
        // completed, important 수정
        Todo todo = todoRepository.findById(3L).get();
        todo.setCompleted(true);
        todo.setImportant(true);
        todoRepository.save(todo);
    }

    @Test
    public void deleteTest() {
        todoRepository.deleteById(10L);
    }

}