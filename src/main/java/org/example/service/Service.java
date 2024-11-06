package org.example.service;

import org.example.exception.TaskNotFoundException;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    void add(T t);

    Optional<T> getById(long id);

    List<T> getAll();

    boolean deleteById(long id) throws TaskNotFoundException;

    T update(long id, T updated) throws TaskNotFoundException;
}
