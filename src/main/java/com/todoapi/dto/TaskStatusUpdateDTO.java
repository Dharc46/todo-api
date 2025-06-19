package com.todoapi.dto;

import com.todoapi.model.Task.TaskStatus;
import jakarta.validation.constraints.NotNull;

public class TaskStatusUpdateDTO {
    
    @NotNull(message = "Status is required")
    private TaskStatus status;
    
    // Constructors
    public TaskStatusUpdateDTO() {}
    
    public TaskStatusUpdateDTO(TaskStatus status) {
        this.status = status;
    }
    
    // Getters and Setters
    public TaskStatus getStatus() {
        return status;
    }
    
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}