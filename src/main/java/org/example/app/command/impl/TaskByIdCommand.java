package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.service.Service;

public class TaskByIdCommand extends Command {
    public TaskByIdCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        String inputId = readInput("Введите ID задачи которую хотите получить");
        service.getById(Long.parseLong(inputId));
    }
}
