package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.exception.ErrorMessage;
import org.example.exception.InputFormatException;
import org.example.exception.TaskNotFoundException;
import org.example.service.Service;
import org.example.util.OptionalUtility;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        boolean result = false;
        try {
            String input = readInput("Введите ID задачи которую хотите удалить");
            int id = OptionalUtility.stringToInteger(input).orElseThrow(()
                    -> new InputFormatException(ErrorMessage.NOT_NUMERIC_VALUE));
            result = service.deleteById(id);
        } catch (TaskNotFoundException | InputFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(result ? "Задача удалена" : "Задача не удалена.");
    }
}
