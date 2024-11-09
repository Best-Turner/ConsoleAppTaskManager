package org.example.app.command;

import org.example.service.Service;
import org.example.util.InputReader;

import java.util.Scanner;

public abstract class Command {

    protected final InputReader reader;
    protected final Service service;

    public Command(Service service, InputReader inputReader) {
        this.reader = inputReader;
        this.service = service;
    }

    public abstract void execute();

    public String readInput(String text) {
        return reader.readInput(text);
    }
}
