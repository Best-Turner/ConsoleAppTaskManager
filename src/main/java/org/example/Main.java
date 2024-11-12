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
import org.example.util.InputReader;
import org.example.util.Parser;

import java.util.Scanner;

public class Main {
    private static final String PATH = "testFile.csv";

    public static void main(String[] args) {

        Parser parser = new CSVParserUtil(PATH);

        TaskRepository repository = new TaskRepositoryImpl(parser);
        TaskServiceImpl service = new TaskServiceImpl(repository);
        InputReader inputReader = new InputReader(new Scanner(System.in));

        Command saveCommand = new SaveTaskCommand(service, inputReader);
        Command showAll = new ShowAllTaskCommand(service, inputReader);
        Command deleteById = new DeleteTaskCommand(service, inputReader);
        Command updateTask = new UpdateTaskCommand(service, inputReader);
        Command exitCommand = new ExitCommand(service, inputReader);


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

    }
}
