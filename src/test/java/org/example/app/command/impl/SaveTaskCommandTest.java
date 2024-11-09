package org.example.app.command.impl;

import org.example.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SaveTaskCommandTest extends CommandTestBase {


    @InjectMocks
    private SaveTaskCommand command;


    @Test
    void whenMethodStartWork() {
        when(reader.readInput("Введите заголовок задачи")).thenReturn("Тестовая задача");
        when(reader.readInput("Введите описание  задачи")).thenReturn("Описание задачи");
        command.execute();
        verify(service).add(Mockito.any(Task.class));
        assertEquals("Задача сохранена!", outputStreamCaptor.toString().trim());
    }
}