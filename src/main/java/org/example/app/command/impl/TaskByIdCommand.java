package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.service.Service;
import org.example.util.InputReader;

import java.util.Optional;

public class TaskByIdCommand extends Command {


    public TaskByIdCommand(Service service, InputReader inputReader) {
        super(service, inputReader);
    }

    @Override
    public void execute() {
        String inputId = readInput("Введите ID задачи которую хотите получить");
        Optional byId = service.getById(Long.parseLong(inputId));
        System.out.println(byId.get());
    }
}
