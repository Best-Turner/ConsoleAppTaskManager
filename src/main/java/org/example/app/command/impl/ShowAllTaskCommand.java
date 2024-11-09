package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.service.Service;
import org.example.util.InputReader;

public class ShowAllTaskCommand extends Command {


    public ShowAllTaskCommand(Service service, InputReader inputReader) {
        super(service, inputReader);
    }

    @Override
    public void execute() {
        service.getAll().forEach(System.out::println);
    }
}
