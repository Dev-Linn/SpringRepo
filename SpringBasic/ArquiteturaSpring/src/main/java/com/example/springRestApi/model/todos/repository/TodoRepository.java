package com.example.springRestApi.model.todos.repository;

import com.example.springRestApi.model.todos.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
}
