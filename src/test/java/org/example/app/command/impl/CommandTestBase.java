package org.example.app.command.impl;

import org.example.model.Task;
import org.example.service.Impl.TaskServiceImpl;
import org.example.util.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class CommandTestBase {

    protected final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    protected final LocalDateTime dateTime = LocalDateTime.now();
    @Mock
    protected TaskServiceImpl service;
    @Mock
    protected InputReader reader;
    protected Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
        task = new Task(1, "Заголовок", "Описание", Task.Priority.LOW, dateTime);
    }
}
