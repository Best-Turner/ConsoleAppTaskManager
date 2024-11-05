package org.example.app;

import org.example.app.menu.IMenu;
import org.example.app.menu.impl.MenuItem;

import java.util.Scanner;
import java.util.Stack;

public class AppLauncher {


    private final IMenu menu;
    private IMenu currentMenu;
    private Stack<IMenu> stack;
    private Scanner scanner;

    public AppLauncher(IMenu menu) {
        this.menu = menu;
        stack = new Stack<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Добро пожаловать в программу по управлению задачами");
        while (true) {
            currentMenu = menu;
            stack.push(currentMenu);
            currentMenu.push();
            System.out.println("\nВедите команду: ");
            String commanad = scanner.nextLine();
            IMenu child = currentMenu.getChild(Integer.parseInt(commanad) - 1);
            if (child instanceof MenuItem) {
                child.push();
            } else {
                currentMenu = child;
                child.push();
            }
        }
    }
}
