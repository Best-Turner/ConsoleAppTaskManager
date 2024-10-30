package org.example.util;

import java.util.List;

public interface Parser<T> {

    List<T> parseFrom();

    void parseTo(List<T> t);

    void clear();
}
