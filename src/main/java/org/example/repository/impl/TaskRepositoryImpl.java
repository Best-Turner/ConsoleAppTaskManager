package org.example.repository.impl;

import org.example.model.Task;
import org.example.repository.TaskRepository;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public void save(Task task) {

    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Task getById(Long aLong) {
        return null;
    }

    @Override
    public Task update(Long aLong, Task updated) {
        return null;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }
}
