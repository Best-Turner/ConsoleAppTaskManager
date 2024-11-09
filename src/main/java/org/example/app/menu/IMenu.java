package org.example.app.menu;

import org.example.exception.InputFormatException;

public abstract class IMenu {

    private static final String ERROR_MESSAGE = "Операция не поддерживается";

    protected String title;

    public IMenu(String title) {
        this.title = title;
    }

    public void push() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    public IMenu getChild(int index) throws InputFormatException {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    public void addChild(IMenu menu) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }


    @Override
    public String toString() {
        return title;
    }
}
