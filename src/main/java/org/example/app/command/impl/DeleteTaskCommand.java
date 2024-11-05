package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.service.Service;

public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        String input = readInput("Введите ID задачи которую хотите удалить");
        boolean result = service.deleteById(Long.parseLong(input));
        System.out.println(result ? "Задача удалена" : "Задача не удалена");
    }
}
