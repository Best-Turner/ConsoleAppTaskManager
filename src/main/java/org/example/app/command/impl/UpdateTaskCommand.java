package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.model.Task;
import org.example.service.Service;

import java.time.LocalDateTime;

public class UpdateTaskCommand extends Command {
    public UpdateTaskCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        String inputId = readInput("Введите ID задачи которую хотите изменить");
        String inputNewTitle = readInput("Введите новый заголовок");
        String inputNewDescription = readInput("Введите новое описание задачи");
        Task.Priority[] priorities = printPriority();
        String inputNewPriority = readInput("Задайте новый приоритет");

        Task updated =
                new Task(inputNewTitle,
                        inputNewDescription,
                        priorities[Integer.parseInt(inputNewPriority) - 1],
                        LocalDateTime.now());
        service.update(Long.parseLong(inputId), updated);
    }


    private Task.Priority[] printPriority() {
        int count = 1;
        Task.Priority[] values = Task.Priority.values();
        for (Task.Priority p : values) {
            System.out.println(count++ + " " + p);
        }
        return values;
    }
}
