package org.example.app.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.command.Command;
import org.example.service.Service;
import org.example.util.InputReader;

public class ExitCommand extends Command {
    private final Logger logger = LogManager.getLogger(ExitCommand.class);

    public ExitCommand(Service service, InputReader inputReader) {
        super(service, inputReader);
    }

    @Override
    public void execute() {
        System.out.println("До скорых встреч =)");
        reader.closeReader();
        logger.info("Завершение работы программы");
        System.exit(0);
    }
}
