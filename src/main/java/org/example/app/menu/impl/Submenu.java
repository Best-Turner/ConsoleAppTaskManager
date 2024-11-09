package org.example.app.menu.impl;

import org.example.app.menu.IMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Submenu extends IMenu {

    private List<IMenu> menuList;
    private Scanner scanner;

    public Submenu(String title) {
        super(title);
        menuList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public IMenu getChild(int index) {
        if (index < 0 || index > menuList.size()) {
            throw new IndexOutOfBoundsException("Команда под номером " + index + " не поддерживается");
        }
        return menuList.get(index - 1);
    }

    @Override
    public void push() {
        this.show();
    }

    @Override
    public void addChild(IMenu menu) {
        menuList.add(menu);
    }

    private void show() {
        int count = 1;
        System.out.println();
        for (IMenu menu : menuList) {
            System.out.println(count++ + " " + menu);
        }
    }


}
