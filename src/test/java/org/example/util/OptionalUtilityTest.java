package org.example.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionalUtilityTest {

    private String notNumber = "a";
    private String number = "5";

    @BeforeEach
    void setUp() {

    }

    @Test
    void whenInputLineNonExistentNumberThenReturnOptionalEmpty() {
        assertEquals(Optional.empty(), OptionalUtility.stringToInteger(notNumber));
    }

    @Test
    void whenInputLineExistNumberThenReturnOptionalInteger() {
        Optional<Integer> expect = Optional.of(5);
        assertEquals(expect, OptionalUtility.stringToInteger(number));
    }
}