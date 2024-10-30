package org.example.service.Impl;

import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.example.service.Service;

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
    public boolean deleteById(long id) {
        List<Task> tasks = getAll();
        boolean contains = tasks.removeIf(task -> task.getId() == id);
        if (contains) {
            repository.clean();
            repository.saveTaskList(tasks);
            return true;
        }
        return false;
    }

    @Override
    public Task update(long id, Task updated) {
        List<Task> tasks = getAll();
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id).findAny();
        if (task.isPresent()) {
            repository.clean();
            Task toUpdate = task.get();
            repository.clean();
            toUpdate.setTitle(updated.getTitle());
            toUpdate.setDescription(updated.getDescription());
            toUpdate.setPriority(updated.getPriority());
            toUpdate.setDate(updated.getDate());
            repository.saveTaskList(tasks);
            return toUpdate;
        }
        return null;
    }

    private long determineId() {
        return getAll().getLast().getId();
    }
}
