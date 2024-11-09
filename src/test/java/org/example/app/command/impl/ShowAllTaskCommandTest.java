package org.example.app.command.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ShowAllTaskCommandTest extends CommandTestBase {

    @InjectMocks
    private ShowAllTaskCommand command;


    @Test
    void testExecute() {
        when(service.getAll()).thenReturn(Collections.singletonList(task));
        command.execute();
        String expect = task.toString();
        assertEquals(expect, outputStreamCaptor.toString().trim());
    }
}