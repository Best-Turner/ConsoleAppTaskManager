package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.model.Task;
import org.example.service.Service;
import org.example.util.InputReader;

import java.time.LocalDateTime;

public class SaveTaskCommand extends Command {

    public SaveTaskCommand(Service service, InputReader inputReader) {
        super(service, inputReader);
    }


    @Override
    public void execute() {
        String title = readInput("Введите заголовок задачи");
        String description = readInput("Введите описание  задачи");
        service.add(new Task(title, description, Task.Priority.LOW, LocalDateTime.now()));
        System.out.println("Задача сохранена!");
    }
}
