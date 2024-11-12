package org.example.repository.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.example.util.Parser;

import java.util.Collections;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    private final Parser parser;
    private final Logger logger = LogManager.getLogger(TaskRepositoryImpl.class);

    public TaskRepositoryImpl(Parser parser) {
        this.parser = parser;
    }


    @Override
    public void save(Task task) {
        logger.debug("Сохранить задачу с ID = {}", task.getId());
        parser.parseTo(Collections.singletonList(task));
    }

    @Override
    public List<Task> getAll() {
        logger.info("Получить список всех задач");
        return parser.parseFrom();
    }

    @Override
    public void clean() {
        logger.debug("Файл задач очищен");
        parser.clear();
    }

    @Override
    public void saveTaskList(List<Task> taskList) {
        logger.debug("Сохранение списка задач");
        parser.parseTo(taskList);
    }
}
