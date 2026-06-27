package com.sakthi.todo.controller;

import com.sakthi.todo.model.ToDoModel;
import com.sakthi.todo.service.ToDoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/todo")
@Slf4j
public class TodoController {
    @Autowired
    ToDoService toDoService;

    @PostMapping("/create")
    ResponseEntity<ToDoModel> createToDoModel(@Valid @RequestBody ToDoModel toDoModel) {
        return new ResponseEntity<>(toDoService.createToDoModel(toDoModel), HttpStatus.CREATED);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Todo Not found!")
    })
    @GetMapping("/{id}")
    ResponseEntity<ToDoModel> getToDoById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(toDoService.getToDoById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.info("Error");
            log.warn("");
            log.error("",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/alltodo")
    ResponseEntity<List<ToDoModel>> getAllToDo() {
        return new ResponseEntity<>(toDoService.getAllToDo(), HttpStatus.OK);
    }

    @GetMapping("/page")
    ResponseEntity<Page<ToDoModel>> getToDoByPages(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(toDoService.getToDoByPages(page, size), HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<ToDoModel> updateToDo(@RequestBody ToDoModel toDoModel) {
        return new ResponseEntity<>(toDoService.updateToDo(toDoModel), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    void deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
    }

}
