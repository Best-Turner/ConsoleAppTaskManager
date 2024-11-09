package org.example.app.menu.impl;

import org.example.app.menu.IMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubmenuTest {
    private Submenu submenu;
    private IMenu mockChildMenu1;
    private IMenu mockChildMenu2;

    @BeforeEach
    public void setUp() {
        submenu = new Submenu("Main Submenu");
        mockChildMenu1 = mock(IMenu.class);
        mockChildMenu2 = mock(IMenu.class);
        when(mockChildMenu1.toString()).thenReturn("Child Menu 1");
        when(mockChildMenu2.toString()).thenReturn("Child Menu 2");
    }

    @Test
    void testAddChild() {
        submenu.addChild(mockChildMenu1);
        submenu.addChild(mockChildMenu2);

        assertEquals(mockChildMenu1, submenu.getChild(1));
        assertEquals(mockChildMenu2, submenu.getChild(2));
    }

    @Test
    void testGetChildWithInvalidIndex() {

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            submenu.getChild(3);
        });
        assertEquals("Команда под номером 3 не поддерживается", exception.getMessage());
    }

    @Test
    void testShow() {
        submenu.addChild(mockChildMenu1);
        submenu.addChild(mockChildMenu2);

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        submenu.push();

        String expectedOutput = "1 Child Menu 1" + System.lineSeparator() + "2 Child Menu 2";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
        System.setOut(System.out);
    }
}