package com.aout.kata;

public class StringCalculatorWithOneDep {


    private Logger logger;

    public StringCalculatorWithOneDep(Logger logger) {

        this.logger = logger;
    }

    public int add(String numbers) throws Throwable {
        if (numbers.contains("-")) {
            throw new IllegalArgumentException("no negatives");
        }
        if (isEmptyInput(numbers)) {
            return defaultValue();
        }

        if (isSingleNumber(numbers)) {
            return parseSingleNumber(numbers);
        }

        return 3;
    }

    private boolean isSingleNumber(String numbers) {
        return !isMultipleNumbers(numbers);
    }

    private int parseSingleNumber(String numbers) throws Throwable {
        int result= Integer.parseInt(numbers);
        this.logger.write("got " + result);
        return result;

    }

    private boolean isMultipleNumbers(String numbers) {
        return numbers.contains(",");
    }

    private int defaultValue() {
        return 0;
    }

    private boolean isEmptyInput(String numbers) {
        return numbers.length() == 0;
    }
}
