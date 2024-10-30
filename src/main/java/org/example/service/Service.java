package org.example.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    void add(T t);

    Optional<T> getById(long id);

    List<T> getAll();

    boolean deleteById(long id);

    T update(long id, T updated);
}
