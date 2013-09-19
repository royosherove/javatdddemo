package com.aout.kata.unittests.unconstrained;

import com.aout.kata.Logger;

public class StringCalculatorWithStatics {

    public int add(String numbers) throws Throwable {
        if (numbers.contains("-")) {
            throw new IllegalArgumentException("no negatives");
        }
        if (isEmptyInput(numbers)){
            return defaultValue();
        }

        if (isSingleNumber(numbers))
            return parseSingleNumber(numbers);

        return 3;
    }

    private boolean isSingleNumber(String numbers) {
        return !isMultipleNumbers(numbers);
    }

    private int parseSingleNumber(String numbers) {
        int result= Integer.parseInt(numbers);

        try {
            StaticLogger.write("got " + result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
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
