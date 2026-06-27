package com.sakthi.todo.service;

import com.sakthi.todo.model.ToDoModel;
import com.sakthi.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDoModel createToDoModel(ToDoModel toDoModel) {
        return toDoRepository.save(toDoModel);
    }

//    By using reference id no exception handling immediately its lazy when particularly like title is asked
//    public ToDoModel getToDoById(Long id){
//        return toDoRepository.getReferenceById(id);
//    }

//    Finding by the id if no then throw error
    public ToDoModel getToDoById(Long id) {
        return toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo List not Found"));
    }

    public Page<ToDoModel> getToDoByPages(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return toDoRepository.findAll(pageable);
    }

    public List<ToDoModel> getAllToDo() {
        return toDoRepository.findAll();
    }

    public ToDoModel updateToDo(ToDoModel toDoModel){
        return toDoRepository.save(toDoModel);
    }

    public void deleteToDo(Long id){
        toDoRepository.delete(getToDoById(id));
    }

}
