package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.service.Service;

public class ShowAllTaskCommand extends Command {
    public ShowAllTaskCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        service.getAll().forEach(System.out::println);
    }
}
