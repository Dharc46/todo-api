package com.todoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.todoapi.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    // Lưu trữ dữ liệu tạm thời trong bộ nhớ
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Bộ đếm ID tự tăng

    @GetMapping
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        long newId = idCounter.getAndIncrement();
        Task newTask = new Task();
        newTask.setId(newId);
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        tasks.put(newId, newTask);
        return newTask;
    }
    
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Task not found with id: " + id
            );
        }
        return task;
    }
    
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        if (!tasks.containsKey(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Task not found with id: " + id
            );
        }
        
        Task task = tasks.get(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        return task;
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        if (!tasks.containsKey(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Task not found with id: " + id
            );
        }
        tasks.remove(id);
    }
}