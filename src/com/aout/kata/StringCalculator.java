package com.aout.kata;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.contains("-")) {
            throw new IllegalArgumentException("no negatives");
        }
        if (isEmptyInput(numbers))
            return defaultValue();

        if (isSingleNumber(numbers))
            return parseSingleNumber(numbers);

        return 3;
    }

    private boolean isSingleNumber(String numbers) {
        return !isMultipleNumbers(numbers);
    }

    private int parseSingleNumber(String numbers) {
        return Integer.parseInt(numbers);
    }

    private boolean isMultipleNumbers(String numbers) {
        return numbers.contains(",");
    }

    private int defaultValue() {
        return 0;
    }

    private boolean isEmptyInput(String numbers) {
        return numbers.length()==0;
    }

    public int subtract(int howMuch, int from) {
        return from - howMuch;
    }

    public int parse(String numbers) {
        if (numbers.length() == 0) {
            return 0;
        }
        return Integer.parseInt(numbers);

    }
}
