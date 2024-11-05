package org.example.app.menu.impl;

import org.example.app.command.Command;
import org.example.app.menu.IMenu;

public class MenuItem extends IMenu {

    private final Command command;

    public MenuItem(String title, Command command) {
        super(title);
        this.command = command;
    }

    @Override
    public void push() {
        command.execute();
    }
}
