package org.example.repository;

import java.util.List;

public interface Repository<T, E> {

    void save(T t);

    List<T> getAll();

}
