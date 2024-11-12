package org.example.service.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exception.TaskNotFoundException;
import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.example.service.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements Service<Task> {
    private final Logger logger = LogManager.getLogger(TaskServiceImpl.class);

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Task task) {
        logger.debug("Добавить задачу {}", task);
        long id = determineId() + 1;
        task.setId(id);
        logger.debug("Добавить задачу c ID = {}", id);
        task.setDate(LocalDateTime.now());
        repository.save(task);
        logger.info("Добавлена задача");
    }

    @Override
    public Optional<Task> getById(long id) {
        logger.debug("Получить задачу с ID = {}", id);
        return repository.getAll().stream()
                .filter(task -> task.getId() == id)
                .peek(e -> logger.info("Получить задачу с ID = {}, возвращена задача - {}", id, e))
                .findAny();
    }

    @Override
    public List<Task> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean deleteById(long id) throws TaskNotFoundException {
        logger.debug("Удалить задачу с ID = {}", id);
        List<Task> tasks = getAll();
        Task task = existTaskById(tasks, id);
        boolean contains = tasks.remove(task);
        if (contains) {
            logger.debug("Задача была удалена из полученного списка");
            repository.clean();
            repository.saveTaskList(tasks);
            logger.debug("Файл перезаписан со списком новых задач");
            logger.info("Задача с ID = {} удалена из списка", id);
            return true;
        }
        logger.warn("Задача с ID = {} не найдена", id);
        return false;
    }

    @Override
    public Task update(long id, Task updated) throws TaskNotFoundException {
        logger.debug("Обновление задачи с ID = {}", id);
        logger.info("Обновление задачи");
        List<Task> tasks = getAll();
        Task toUpdate = existTaskById(tasks, id);
        repository.clean();
        String newTitle = updated.getTitle();
        if (!newTitle.isEmpty()) {
            logger.debug("Обновление заголовка. Новый заголовок - '{}'", newTitle);
            toUpdate.setTitle(newTitle);
        }
        String newDescription = updated.getDescription();
        if (!newDescription.isEmpty()) {
            logger.debug("Обновление описания. Новое описание - '{}'", newDescription);
            toUpdate.setDescription(newDescription);
        }
        if (updated.getPriority() != null) {
            logger.debug("Обновление приоритета. Новый приоритет - '{}'", updated.getPriority());
            toUpdate.setPriority(updated.getPriority());
        }
        toUpdate.setDate(updated.getDate());
        logger.debug("Новая дата сохранения");
        repository.saveTaskList(tasks);
        logger.info("Задача обновлена");
        return toUpdate;
    }

    private long determineId() {
        logger.debug("Получение ID пред идущей задачи");
        if (getAll().isEmpty()) {
            logger.debug("Список задач пуст");
            return 0;
        }
        long lastId = getAll().getLast().getId();
        logger.debug("Последний ID задача в списке - {}", lastId);
        return lastId;
    }

    private Task existTaskById(List<Task> tasks, long id) throws TaskNotFoundException {
        logger.debug("Проверка - существует ли задача с ID  = {}", id);
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id).findAny();
        if (!task.isPresent()) {
            logger.debug("Задача с ID = {} не найдена", id);
            throw new TaskNotFoundException("Задача с ID = " + id + " не найдена");
        }
        logger.debug("Задача с ID = {} найдена", id);
        return task.get();
    }
}
