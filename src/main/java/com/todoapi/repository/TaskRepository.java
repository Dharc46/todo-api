package com.todoapi.repository;

import com.todoapi.model.Task;
import com.todoapi.model.Task.TaskStatus;
import com.todoapi.model.Task.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Find tasks by status
    List<Task> findByStatus(TaskStatus status);
    
    // Find tasks by priority
    List<Task> findByPriority(TaskPriority priority);
    
    // Find tasks by status and priority
    List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);
    
    // Find tasks containing title (case-insensitive)
    List<Task> findByTitleContainingIgnoreCase(String title);
    
    // Find tasks containing description (case-insensitive)
    List<Task> findByDescriptionContainingIgnoreCase(String description);
    
    // Find tasks by due date range
    List<Task> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find overdue tasks
    @Query("SELECT t FROM Task t WHERE t.dueDate < ?1 AND t.status != 'COMPLETED'")
    List<Task> findOverdueTasks(LocalDateTime currentDateTime);
    
    // Find tasks created in date range
    List<Task> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find tasks by multiple statuses
    List<Task> findByStatusIn(List<TaskStatus> statuses);
    
    // Count tasks by status
    long countByStatus(TaskStatus status);
    
    // Count tasks by priority
    long countByPriority(TaskPriority priority);
    
    // Custom query to find tasks with specific criteria
    @Query("SELECT t FROM Task t WHERE " +
           "(:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:status IS NULL OR t.status = :status) AND " +
           "(:priority IS NULL OR t.priority = :priority)")
    List<Task> findTasksWithCriteria(@Param("title") String title,
                                   @Param("status") TaskStatus status,
                                   @Param("priority") TaskPriority priority);
    
    // Find recent tasks (created in last N days)
    @Query("SELECT t FROM Task t WHERE t.createdAt >= :startDate ORDER BY t.createdAt DESC")
    List<Task> findRecentTasks(@Param("startDate") LocalDateTime startDate);
    
    // Find tasks due soon (within next N days)
    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN ?1 AND ?2 AND t.status != 'COMPLETED' ORDER BY t.dueDate ASC")
    List<Task> findTasksDueSoon(LocalDateTime now, LocalDateTime futureDate);
    
    // Get task statistics
    @Query("SELECT " +
           "SUM(CASE WHEN t.status = 'TODO' THEN 1 ELSE 0 END) as todoCount, " +
           "SUM(CASE WHEN t.status = 'IN_PROGRESS' THEN 1 ELSE 0 END) as inProgressCount, " +
           "SUM(CASE WHEN t.status = 'COMPLETED' THEN 1 ELSE 0 END) as completedCount, " +
           "SUM(CASE WHEN t.status = 'CANCELLED' THEN 1 ELSE 0 END) as cancelledCount " +
           "FROM Task t")
    Object[] getTaskStatistics();
    
    // Delete completed tasks older than specified date
    void deleteByStatusAndUpdatedAtBefore(TaskStatus status, LocalDateTime date);
    
    // Find tasks ordered by priority and due date
    @Query("SELECT t FROM Task t ORDER BY " +
           "CASE t.priority " +
           "  WHEN 'URGENT' THEN 1 " +
           "  WHEN 'HIGH' THEN 2 " +
           "  WHEN 'MEDIUM' THEN 3 " +
           "  WHEN 'LOW' THEN 4 " +
           "END, " +
           "t.dueDate ASC NULLS LAST")
    List<Task> findAllOrderedByPriorityAndDueDate();
}