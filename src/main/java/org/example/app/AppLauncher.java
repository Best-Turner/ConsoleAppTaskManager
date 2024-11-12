package org.example.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.menu.IMenu;
import org.example.app.menu.impl.MenuItem;
import org.example.exception.ErrorMessage;
import org.example.exception.InputFormatException;
import org.example.util.OptionalUtility;

import java.util.Scanner;

public class AppLauncher {

    private final IMenu menu;
    private final Logger logger = LogManager.getLogger(AppLauncher.class);
    private IMenu currentMenu;
    private Scanner scanner;

    public AppLauncher(IMenu menu) {
        this.menu = menu;
        scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("Добро пожаловать в программу по управлению задачами");
        logger.info("Старт работы программы");
        while (true) {
            try {
                currentMenu = menu;
                currentMenu.push();
                System.out.println("\nВедите команду: ");
                String commanad = scanner.nextLine();
                int index = OptionalUtility.stringToInteger(commanad)
                        .orElseThrow(() -> new InputFormatException(ErrorMessage.NOT_NUMERIC_VALUE));
                IMenu child = currentMenu.getChild(index);
                if (child instanceof MenuItem) {
                    child.push();
                } else {
                    currentMenu = child;
                    child.push();
                }
            } catch (InputFormatException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
