package org.example.service.Impl;

import org.example.exception.TaskNotFoundException;
import org.example.model.Task;
import org.example.repository.impl.TaskRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    TaskRepositoryImpl repository;

    @InjectMocks
    TaskServiceImpl service;

    private Task task;
    private long id = 1l;
    private List<Task> tasks;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task(id, "title", "description", Task.Priority.LOW, LocalDateTime.now());
        tasks = new ArrayList<>();
    }


    @Test
    void whenGetByIdThenReturnTask() {
        when(repository.getAll()).thenReturn(Collections.singletonList(task));
        Optional<Task> result = service.getById(1L);
        assertTrue(result.isPresent());
        assertEquals(task, result.get());
    }

    @Test
    void whenGetByIdWithNonExistentIdThenReturnEmpty() {
        when(repository.getAll()).thenReturn(Collections.emptyList());
        Optional<Task> result = service.getById(999L);
        assertTrue(result.isEmpty());
    }

    @Test
    void whenUpdateExistingTaskThenReturnUpdatedTask() throws TaskNotFoundException {
        Task existingTask = new Task(1L, "Old Title", "Old Description", null, null);
        tasks.add(existingTask);
        when(repository.getAll()).thenReturn(tasks);

        Task updatedTask = new Task(1L, "New Title", "New Description", null, null);

        Task result = service.update(1L, updatedTask);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
        assertEquals(existingTask, result);
        verify(repository).clean();
        verify(repository).saveTaskList(tasks);
    }

    @Test
    void whenUpdateNonExistentTaskThenThrowException() {
        when(repository.getAll()).thenReturn(Collections.emptyList());

        Task updatedTask = new Task(999L, "New Title", "New Description", null, null);

        assertThrows(TaskNotFoundException.class, () -> service.update(999L, updatedTask));
    }

    @Test
    void whenDeleteNonExistentTaskThenThrowTaskNotFoundException() throws TaskNotFoundException {
        when(repository.getAll()).thenReturn(Collections.emptyList());
        assertThrows(TaskNotFoundException.class, () -> service.deleteById(999), "Задача с ID = " + 999 + " не найдена");
    }

    @Test
    void whenDeleteExistentTaskThenReturnTrue() throws TaskNotFoundException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(repository.getAll()).thenReturn(tasks);
        assertTrue(service.deleteById(id));
    }

    @Test
    void whenAddTaskThenTaskMustBeSave() {
        service.add(task);
        verify(repository, times(1)).save(task);
    }


}

