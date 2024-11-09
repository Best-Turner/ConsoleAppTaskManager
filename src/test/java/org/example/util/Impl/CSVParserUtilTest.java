package org.example.util.Impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVParserUtilTest {
    private CSVParserUtil parserUtil;
    private String testFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        // Создаем временный файл для тестов
        testFilePath = "test.csv";
        FileWriter writer = new FileWriter(testFilePath);
        writer.close();
        parserUtil = new CSVParserUtil(testFilePath);
    }

    @Test
    void testParseFrom() throws IOException {
        // Arrange
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(testFilePath), CSVFormat.MYSQL)) {
            printer.printRecord(1L, "Test Task", "Description", "HARD", LocalDateTime.now().toString());
            printer.printRecord(2L, "Another Task", "Another Description", "LOW", LocalDateTime.now().toString());
        }
        // Act
        List<Task> tasks = parserUtil.parseFrom();
        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
        assertEquals(Task.Priority.HARD, tasks.get(0).getPriority());
        assertEquals("Another Task", tasks.get(1).getTitle());
        assertEquals(Task.Priority.LOW, tasks.get(1).getPriority());
    }

    @Test
    void testParseTo() throws IOException {
        // Arrange
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test Task", "Description", Task.Priority.HARD, LocalDateTime.now()));
        tasks.add(new Task(2L, "Another Task", "Another Description", Task.Priority.LOW, LocalDateTime.now()));
        // Act
        parserUtil.parseTo(tasks);
        // Assert
        List<Task> parsedTasks = parserUtil.parseFrom();
        assertEquals(2, parsedTasks.size());
        assertEquals("Test Task", parsedTasks.get(0).getTitle());
        assertEquals(Task.Priority.HARD, parsedTasks.get(0).getPriority());
        assertEquals("Another Task", parsedTasks.get(1).getTitle());
        assertEquals(Task.Priority.LOW, parsedTasks.get(1).getPriority());
    }

    @Test
    void testClear() throws IOException {
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Заголовок1", "Описание1", Task.Priority.LOW, LocalDateTime.now()),
                new Task(1L, "Заголовок2", "Описание2", Task.Priority.HARD, LocalDateTime.now()));
        parserUtil.parseTo(tasks);
        assertEquals(2, parserUtil.parseFrom().size());
        parserUtil.clear();
        assertEquals(0, parserUtil.parseFrom().size());
    }
}