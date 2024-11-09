package org.example.app.command.impl;

import org.example.app.command.Command;
import org.example.service.Service;
import org.example.util.InputReader;

public class ExitCommand extends Command {


    public ExitCommand(Service service, InputReader inputReader) {
        super(service, inputReader);
    }

    @Override
    public void execute() {
        System.out.println("До скорых встреч =)");
        reader.closeReader();
        System.exit(0);
    }
}
