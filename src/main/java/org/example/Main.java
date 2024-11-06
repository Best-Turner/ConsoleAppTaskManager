package org.example;

import org.example.app.AppLauncher;
import org.example.app.command.Command;
import org.example.app.command.impl.*;
import org.example.app.menu.IMenu;
import org.example.app.menu.impl.MenuItem;
import org.example.app.menu.impl.Submenu;
import org.example.repository.TaskRepository;
import org.example.repository.impl.TaskRepositoryImpl;
import org.example.service.Impl.TaskServiceImpl;
import org.example.util.Impl.CSVParserUtil;
import org.example.util.Parser;

public class Main {
    private static final String PATH = "file1.csv";

    public static void main(String[] args) {

        Parser parser = new CSVParserUtil(PATH);

        TaskRepository repository = new TaskRepositoryImpl(parser);
        TaskServiceImpl service = new TaskServiceImpl(repository);

        Command saveCommand = new SaveTaskCommand(service);
        Command showAll = new ShowAllTaskCommand(service);
        Command deleteById = new DeleteTaskCommand(service);
        Command updateTask = new UpdateTaskCommand(service);
        Command exitCommand = new ExitCommand(service);


        IMenu mainMenu = new Submenu("Главное меню");

        IMenu registerMenu = new MenuItem("Создать новую задачу", saveCommand);
        IMenu allTasks = new MenuItem("Показать все задачи", showAll);
        IMenu update = new MenuItem("Изменить задачу", updateTask);
        IMenu delete = new MenuItem("Удалить по ID", deleteById);
        IMenu exit = new MenuItem("Закрыть программу", exitCommand);


        mainMenu.addChild(registerMenu);
        mainMenu.addChild(allTasks);
        mainMenu.addChild(update);
        mainMenu.addChild(delete);
        mainMenu.addChild(exit);

        AppLauncher app = new AppLauncher(mainMenu);

        app.start();

        // для тестов
//
//        int coint = 1;
//        try (CSVPrinter printer = new CSVPrinter(new FileWriter("file1.csv"), CSVFormat.MYSQL)) {

//
//            printer.printRecord(coint++, "Task1", "SomeDescription-1", "LOW", LocalDateTime.now());
//            printer.printRecord(coint++, "Task2", "SomeDescription-2", "HARD", LocalDateTime.now());
//            printer.printRecord(coint++, "Task3", "SomeDescription-3", "MIDDLE", LocalDateTime.now());
//            printer.printRecord(coint, "Task4", "SomeDescription-4", "HARD", LocalDateTime.now());

//            for (int i = 0; i < 1000; i++) {
//                printer.printRecord(coint++, "Task - " + i,
//                        "SomeDescription - " + i,
//                        i % 2 == 0 ? "LOW" : "HARD",
//                        LocalDateTime.now());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
