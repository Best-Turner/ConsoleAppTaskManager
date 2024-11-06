package org.example.util;

import java.util.Optional;

public class OptionalUtility {

    public static Optional<Integer> stringToInteger(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}

