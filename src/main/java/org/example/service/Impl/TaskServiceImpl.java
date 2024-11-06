package org.example.service.Impl;

import org.example.exception.TaskNotFoundException;
import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.example.service.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements Service<Task> {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Task task) {
        task.setId(determineId() + 1);
        task.setDate(LocalDateTime.now());
        repository.save(task);
    }

    @Override
    public Optional<Task> getById(long id) {
        return repository.getAll().stream()
                .filter(task -> task.getId() == id).findAny();
    }

    @Override
    public List<Task> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean deleteById(long id) throws TaskNotFoundException {
        List<Task> tasks = getAll();
        Task task = existTaskById(tasks, id);
        boolean contains = tasks.remove(task);
        if (contains) {
            repository.clean();
            repository.saveTaskList(tasks);
            return true;
        }
        return false;
    }

    @Override
    public Task update(long id, Task updated) throws TaskNotFoundException {
        List<Task> tasks = getAll();
        Task toUpdate = existTaskById(tasks, id);
        repository.clean();
        String newTitle = updated.getTitle();
        if (!newTitle.isEmpty()) {
            toUpdate.setTitle(newTitle);
        }
        String newDescription = updated.getDescription();
        if (!newDescription.isEmpty()) {
            toUpdate.setDescription(newDescription);
        }
        if (updated.getPriority() != null) {
            toUpdate.setPriority(updated.getPriority());
        }
        toUpdate.setDate(updated.getDate());
        repository.saveTaskList(tasks);
        return toUpdate;
    }

    private long determineId() {
        if (getAll().isEmpty()) {
            return 0;
        }
        return getAll().getLast().getId();
    }

    private Task existTaskById(List<Task> tasks, long id) throws TaskNotFoundException {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id).findAny();
        if (!task.isPresent()) {
            throw new TaskNotFoundException("Задача с ID = " + id + " не найдена");
        }
        return task.get();
    }
}
