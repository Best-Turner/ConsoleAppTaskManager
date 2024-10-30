package org.example.util.Impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.example.model.Task;
import org.example.util.Parser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParserUtil implements Parser<Task> {

    private String path;

    public CSVParserUtil(String path) {
        this.path = path;
    }

    @Override
    public List<Task> parseFrom() {
        try (CSVParser parser = new CSVParser(new FileReader(path), CSVFormat.MYSQL)) {
            List<CSVRecord> records = parser.getRecords();
            return records.stream()
                    .map(el -> new Task(Long.parseLong(el.get(0)),
                            el.get(1),
                            el.get(2),
                            Task.Priority.valueOf(el.get(3)),
                            LocalDateTime.parse(el.get(4))))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Parse failed =(");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void parseTo(List<Task> tasks) {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(path, true), CSVFormat.MYSQL)) {
            for (Task task : tasks) {
                printer.printRecord(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getPriority(),
                        task.getDate());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        try (FileWriter writer = new FileWriter(path, false)) {
            System.out.println("Файл очищен");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
