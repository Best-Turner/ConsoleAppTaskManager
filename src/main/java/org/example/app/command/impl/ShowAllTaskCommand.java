package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.service.Service;

public class ShowAllTaskCommand extends Command {
    public ShowAllTaskCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        long before = System.currentTimeMillis();
        service.getAll().forEach(System.out::println);
        long after = System.currentTimeMillis();
        System.out.println("Время загрузки данных  = " + (after - before));
    }
}
