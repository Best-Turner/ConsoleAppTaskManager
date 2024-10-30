package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.repository.TaskRepository;
import org.example.repository.impl.TaskRepositoryImpl;
import org.example.service.Impl.TaskServiceImpl;
import org.example.util.Impl.CSVParserUtil;
import org.example.util.Parser;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Parser parser = new CSVParserUtil("file1.csv");

        TaskRepository repository = new TaskRepositoryImpl(parser);
        TaskServiceImpl service = new TaskServiceImpl(repository);

        // для тестов

        int coint = 1;
        try (CSVPrinter printer = new CSVPrinter(new FileWriter("file1.csv"), CSVFormat.MYSQL)) {
            printer.printRecord(coint++, "Task1", "SomeDescription-1", "LOW", LocalDateTime.now());
            printer.printRecord(coint++, "Task2", "SomeDescription-2", "HARD", LocalDateTime.now());
            printer.printRecord(coint++, "Task3", "SomeDescription-3", "MIDDLE", LocalDateTime.now());
            printer.printRecord(coint, "Task4", "SomeDescription-4", "HARD", LocalDateTime.now());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
