// TaskService.java (Interface)
package com.todoapi.service;

import com.todoapi.dto.*;
import com.todoapi.model.Task;
import com.todoapi.model.Task.TaskStatus;
import com.todoapi.model.Task.TaskPriority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TaskService {
    
    // Basic CRUD operations
    TaskResponseDTO createTask(TaskCreateDTO taskCreateDTO);
    TaskResponseDTO getTaskById(Long id);
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO updateTask(Long id, TaskUpdateDTO taskUpdateDTO);
    TaskResponseDTO updateTaskStatus(Long id, TaskStatusUpdateDTO statusUpdateDTO);
    void deleteTask(Long id);
    
    // Search and filter operations
    List<TaskResponseDTO> getTasksByStatus(TaskStatus status);
    List<TaskResponseDTO> getTasksByPriority(TaskPriority priority);
    List<TaskResponseDTO> searchTasksByTitle(String title);
    List<TaskResponseDTO> getTasksWithCriteria(String title, TaskStatus status, TaskPriority priority);
    
    // Date-based operations
    List<TaskResponseDTO> getOverdueTasks();
}