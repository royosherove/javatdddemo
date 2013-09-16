package com.aout.kata;


public class StringCalculatorWithTwoDep {


    private Logger logger;
    private WebService service;

    public StringCalculatorWithTwoDep(Logger logger,WebService service) {

        this.logger = logger;
        this.service = service;
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
