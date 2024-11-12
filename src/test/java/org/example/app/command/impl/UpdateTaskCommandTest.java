package org.example.app.command.impl;

import org.example.exception.TaskNotFoundException;
import org.example.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UpdateTaskCommandTest extends CommandTestBase {


    @InjectMocks
    private UpdateTaskCommand command;

    @Test
    void executeSuccess() throws TaskNotFoundException {
        when(reader.readInput("Введите ID задачи которую хотите изменить")).thenReturn("1");
        when(reader.readInput("Введите новый заголовок")).thenReturn("Новый заголовок");
        when(reader.readInput("Введите новое описание задачи")).thenReturn("Новое описание");
        when(reader.readInput("Задайте новый приоритет")).thenReturn("1");

        command.execute();

        Task updatedTask = new Task();
        updatedTask.setTitle("Новый заголовок");
        updatedTask.setDescription("Новое описание");
        updatedTask.setDate(task.getDate());
        updatedTask.setPriority(Task.Priority.LOW);

    }

    @Test
    void testExecuteWithInputFormatException() {
        when(reader.readInput("Введите ID задачи которую хотите изменить")).thenReturn("abc");

        command.execute();

        assertEquals("Вы ввели не числовое значение.", outputStreamCaptor.toString().trim()); // Проверяем, что сообщение об ошибке было выведено

    }
}
