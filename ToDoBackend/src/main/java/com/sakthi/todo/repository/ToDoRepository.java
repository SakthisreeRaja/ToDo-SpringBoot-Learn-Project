package com.sakthi.todo.repository;

import com.sakthi.todo.model.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {

}
