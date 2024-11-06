package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.exception.ErrorMessage;
import org.example.exception.InputFormatException;
import org.example.exception.TaskNotFoundException;
import org.example.model.Task;
import org.example.service.Service;
import org.example.util.OptionalUtility;

import java.time.LocalDateTime;

public class UpdateTaskCommand extends Command {


    public UpdateTaskCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        int count = -1;
        try {
            String inputId = readInput("Введите ID задачи которую хотите изменить");
            int id = OptionalUtility.stringToInteger(inputId).filter(i -> i > 0).orElseThrow(()
                    -> new InputFormatException(ErrorMessage.NOT_NUMERIC_VALUE));
            String inputNewTitle = readInput("Введите новый заголовок");
            String inputNewDescription = readInput("Введите новое описание задачи");
            Task.Priority[] priorities = printPriority();
            count = priorities.length;
            String inputNewPriority = readInput("Задайте новый приоритет");
            Task updated = new Task();
            updated.setTitle(inputNewTitle);
            updated.setDescription(inputNewDescription);
            updated.setDate(LocalDateTime.now());
            if (!inputNewPriority.isBlank()) {
                int indexNewPriority = OptionalUtility.stringToInteger(inputNewPriority).orElseThrow(()
                        -> new InputFormatException(ErrorMessage.NOT_NUMERIC_VALUE));
                updated.setPriority(priorities[indexNewPriority - 1]);
            }

            service.update(id, updated);
        } catch (InputFormatException | TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(String.format("приоритет задается цифрами от %d до %d", 1, count));
        }
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
