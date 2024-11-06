package org.example.repository.impl;

import org.example.model.Task;
import org.example.util.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.*;


class TaskRepositoryImplTest {


    @Mock
    Parser parser;
    @InjectMocks
    TaskRepositoryImpl repository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void whenGetAllThenReturnList() {
        when(parser.parseFrom()).thenReturn(Collections.emptyList());
        repository.getAll();
        verify(parser, times(1)).parseFrom();
    }

    @Test
    void testSave() {
        Task task = new Task();
        repository.save(task);
        verify(parser, times(1)).parseTo(Collections.singletonList(task));
    }

    @Test
    void testClear() {
        repository.clean();
        verify(parser, times(1)).clear();
    }

    @Test
    void testSaveList() {
        Task task = new Task();
        repository.saveTaskList(Collections.singletonList(task));
        verify(parser, times(1)).parseTo(Collections.singletonList(task));
    }

}