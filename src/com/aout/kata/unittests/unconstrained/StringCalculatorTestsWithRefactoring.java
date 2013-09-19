package com.aout.kata.unittests.unconstrained;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

public class StringCalculatorTestsWithRefactoring {

    class TestableStringCalculatorWithStatics extends StringCalculatorWithStatics{
        public String written;

        @Override
       protected void callLogger(String text) throws Throwable {
           written = text;
       }
    }

    @Test
    public void add_always_callsStaticLogger() throws Throwable {
        TestableStringCalculatorWithStatics sc = new TestableStringCalculatorWithStatics();

        sc.add("1");

        assertEquals("got 1", sc.written);
    }
}

