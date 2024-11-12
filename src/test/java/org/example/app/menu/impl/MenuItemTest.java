package org.example.app.menu.impl;

import org.example.app.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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