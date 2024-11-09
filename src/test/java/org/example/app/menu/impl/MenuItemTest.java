package org.example.app.menu.impl;

import org.example.app.command.Command;
import org.example.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuItemTest {

    @Mock
    private Command command;

    @InjectMocks
    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPush() {
        menuItem.push();
        verify(command, times(1)).execute();
    }

}