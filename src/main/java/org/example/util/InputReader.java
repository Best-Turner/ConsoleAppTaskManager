package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class InputReader {
    private final Logger logger = LogManager.getLogger(InputReader.class);
    private Scanner scanner;


    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInput(String text) {
        logger.debug("Чтение данных: {}", text);
        System.out.println(text);

        String input = scanner.nextLine();
        logger.debug("Ввод пользователя: {}", input);
        return input;
    }

    public void closeReader() {
        logger.debug("Сканер закрыт");
        scanner.close();
    }
}
