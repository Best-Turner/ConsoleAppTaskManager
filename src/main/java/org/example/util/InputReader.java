package org.example.util;

import java.util.Scanner;

public class InputReader {

    private Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInput(String text) {
        System.out.println(text);
        return scanner.nextLine();
    }

    public void closeReader() {
        scanner.close();
    }
}
