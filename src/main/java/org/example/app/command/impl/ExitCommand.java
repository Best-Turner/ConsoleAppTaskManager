package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.service.Service;

public class ExitCommand extends Command {


    public ExitCommand(Service service) {
        super(service);
    }

    @Override
    public void execute() {
        System.out.println("До скорых встреч =)");
        scanner.close();
        System.exit(0);
    }
}
