package com.todoapi.dto;

import com.todoapi.model.Task;
import com.todoapi.model.Task.TaskPriority;
import com.todoapi.model.Task.TaskStatus;

import java.time.LocalDateTime;

public class TaskResponseDTO {
    
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
    private String statusDisplayName;
    private String priorityDisplayName;
    private boolean isOverdue;
    
    // Constructors
    public TaskResponseDTO() {}
    
    public TaskResponseDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
        this.dueDate = task.getDueDate();
        this.statusDisplayName = task.getStatus().getDisplayName();
        this.priorityDisplayName = task.getPriority().getDisplayName();
        this.isOverdue = task.getDueDate() != null && 
                        task.getDueDate().isBefore(LocalDateTime.now()) && 
                        task.getStatus() != TaskStatus.COMPLETED;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public TaskStatus getStatus() {
        return status;
    }
    
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    
    public TaskPriority getPriority() {
        return priority;
    }
    
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    
    public String getStatusDisplayName() {
        return statusDisplayName;
    }
    
    public void setStatusDisplayName(String statusDisplayName) {
        this.statusDisplayName = statusDisplayName;
    }
    
    public String getPriorityDisplayName() {
        return priorityDisplayName;
    }
    
    public void setPriorityDisplayName(String priorityDisplayName) {
        this.priorityDisplayName = priorityDisplayName;
    }
    
    public boolean isOverdue() {
        return isOverdue;
    }
    
    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }
}