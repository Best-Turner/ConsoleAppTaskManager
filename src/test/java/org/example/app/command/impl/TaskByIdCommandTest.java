package org.example.app.command.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TaskByIdCommandTest extends CommandTestBase {

    @InjectMocks
    private TaskByIdCommand command;

    @Test
    void testExecute() {
        when(reader.readInput("Введите ID задачи которую хотите получить")).thenReturn("1");
        when(service.getById(1)).thenReturn(Optional.of(task));
        command.execute();
        assertEquals(task.toString().trim(), outputStreamCaptor.toString().trim());
    }

}