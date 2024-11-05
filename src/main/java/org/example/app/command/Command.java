package org.example.app.command;

import org.example.service.Service;

import java.util.Scanner;

public abstract class Command {

    protected final Scanner scanner;
    protected final Service service;

    public Command(Service service) {
        this.scanner = new Scanner(System.in);
        this.service = service;
    }

    public abstract void execute();

    public String readInput(String text) {
        System.out.println(text);
        return scanner.nextLine();
    }
}
