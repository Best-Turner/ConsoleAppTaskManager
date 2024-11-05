package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.model.Task;
import org.example.service.Service;

import java.time.LocalDateTime;

public class SaveTaskCommand extends Command {

    public SaveTaskCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        String title = readInput("Введите заголовок задачи");
        String description = readInput("Введите описание  задачи");
        String date = readInput("Введите дату задачи");

        service.add(new Task(title, description, Task.Priority.LOW, LocalDateTime.parse(date)));

        System.out.println("Задача сохранена!");
    }
}
