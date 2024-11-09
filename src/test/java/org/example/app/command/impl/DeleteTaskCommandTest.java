package org.example.app.command.impl;

import org.example.exception.InputFormatException;
import org.example.exception.TaskNotFoundException;
import org.example.service.Impl.TaskServiceImpl;
import org.example.util.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteTaskCommandTest extends CommandTestBase{

    @InjectMocks
    private DeleteTaskCommand command;

    @Test
    void whenDeleteExistentTaskThenReturnMessageTaskWasDeleted() throws TaskNotFoundException {
        when(service.deleteById(1)).thenReturn(true);
        when(reader.readInput("Введите ID задачи которую хотите удалить")).thenReturn("1");
        command.execute();
        verify(service, times(1)).deleteById(1);
        assertEquals("Задача удалена", outputStreamCaptor.toString().trim());
    }


    @Test
    void whenInputStringIsNotNumber() {
        when(reader.readInput("Введите ID задачи которую хотите удалить")).thenReturn("a");
        command.execute();
        assertTrue(outputStreamCaptor.toString().trim().contains("Вы ввели не числовое значение."));
    }
}