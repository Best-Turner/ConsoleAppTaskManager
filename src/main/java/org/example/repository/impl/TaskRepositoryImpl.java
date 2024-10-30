package org.example.repository.impl;

import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.example.util.Parser;

import java.util.Collections;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    private final Parser parser;

    public TaskRepositoryImpl(Parser parser) {
        this.parser = parser;
    }


    @Override
    public void save(Task task) {
        parser.parseTo(Collections.singletonList(task));
    }

    @Override
    public List<Task> getAll() {
        return parser.parseFrom();
    }

    @Override
    public void clean() {
        parser.clear();
    }

    @Override
    public void saveTaskList(List<Task> taskList) {
        parser.parseTo(taskList);
    }
}
