package com.sakthi.todo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
//@Data lombok automatic getter & setter
public class ToDoModel {

    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    @Schema(name = "title", example = "xxxDaily Routines")
    String title;
    @NotBlank
    @Size(min = 0,max = 20)
    //for regex of phone num @Pattern(regexp = "^[0-9]{10}$")
    String description;
    boolean completed;
    @Email
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() { return id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
