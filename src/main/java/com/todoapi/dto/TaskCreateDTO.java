package com.todoapi.dto;

import com.todoapi.model.Task.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TaskCreateDTO {
    
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    private TaskPriority priority = TaskPriority.MEDIUM;
    
    private LocalDateTime dueDate;
    
    // Constructors
    public TaskCreateDTO() {}
    
    public TaskCreateDTO(String title, String description, TaskPriority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
    
    // Getters and Setters
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
    
    public TaskPriority getPriority() {
        return priority;
    }
    
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
    
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}