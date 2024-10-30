package org.example.repository;

import org.example.model.Task;

import java.util.List;

public interface TaskRepository extends Repository<Task, Long> {

    void clean();

    void saveTaskList(List<Task> taskList);
}
