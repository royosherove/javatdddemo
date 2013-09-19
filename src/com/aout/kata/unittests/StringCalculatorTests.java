package com.aout.kata.unittests;

import com.aout.kata.Logger;
import com.aout.kata.StringCalculator;
import com.aout.kata.TraceMessage;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.*;


public class StringCalculatorTests {

    private StringCalculator makeCalc() {
        return new StringCalculator(new TheFakeLogger());
    }

    private void assertAdding(String numbers, int expected) throws Throwable {
        StringCalculator sc = makeCalc();

        int result = sc.add(numbers);

        assertEquals(expected, result);
    }

    class TheFakeLogger implements Logger {
        public String written;
        private Throwable error;

        public void writeMessage(TraceMessage text) {
        }
        public void write(String text) throws Throwable {
            written = text;
            if (error != null) {
                throw error;
            }
        }

        public void willThrow(Throwable error) {
            this.error = error;
        }
    }

    @Test
    public void add_always_callsLogger() throws Throwable {
        TheFakeLogger mockLog = new TheFakeLogger();
        StringCalculator sc = new StringCalculator(mockLog);

        sc.add("");

        assertEquals("got 0", mockLog.written);
    }


    @Test
    public void parse_emptyString_returnsZero() {
        StringCalculator stringCalculator = makeCalc();

        int result = stringCalculator.parse("");

        assertEquals(0,result);
    }

    @Test
    public void parse_singleNumber_returnsThatNumber() {
        StringCalculator stringCalculator = makeCalc();

        int result = stringCalculator.parse("1");

        assertEquals(1,result);
    }

    @Test
    public void parse_singleNumber_returnsThatNumber2() {
        StringCalculator stringCalculator = makeCalc();

        int result = stringCalculator.parse("2");

        assertEquals(2,result);
    }




    @Test
    public void subtract_twoSimpleNumbers_subtractsThem() {
        StringCalculator stringCalculator = makeCalc();

        int result = stringCalculator.subtract(1, 2);

        assertEquals(1, result);
    }













    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void add_negative_throws2() throws Throwable {
        StringCalculator calc = makeCalc();

        thrown.expect(IllegalArgumentException.class);
        calc.add("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_negative_throws1() throws Throwable {
        makeCalc().add("-1");
    }


    @Test
    public void add_negative_throws0() {
        StringCalculator stringCalculator = makeCalc();
        try {
            stringCalculator.add("-1");
        } catch (Throwable e) {
            //all good
            return;
        }
        assertFalse(true);

    }
    @Test
    public void add_multipleNumbers_returnstheSum() throws Throwable {
        assertAdding("1,2", 3);
    }

    @Test
    public void add_singleNumber_returnsThatNumber() throws Throwable {
        assertAdding("1", 1);
    }



    @Test
    public void add_emptyString_returnsZero() throws Throwable {
        assertAdding("",0);
    }
}
