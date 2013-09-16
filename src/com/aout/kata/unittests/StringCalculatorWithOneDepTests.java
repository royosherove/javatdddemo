package com.aout.kata.unittests;

import com.aout.kata.StringCalculator;
import com.aout.kata.StringCalculatorWithOneDep;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;


public class StringCalculatorWithOneDepTests {

    private StringCalculatorWithOneDep makeCalc() {
        return new StringCalculatorWithOneDep(new FakeLogger());
    }

    private void assertAdding(String numbers, int expected) throws InterruptedException {
        StringCalculatorWithOneDep sc = makeCalc();

        int result = sc.add(numbers);

        assertEquals(expected, result);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void add_whenCalled_callsLogger() throws InterruptedException {
        FakeLogger mockLog = new FakeLogger();
        StringCalculatorWithOneDep sc = new StringCalculatorWithOneDep(mockLog);

        sc.add("1");

        assertEquals("got 1", mockLog.writtenText);
    }

    @Test
    public void add_negative_throws2() throws InterruptedException {
        StringCalculatorWithOneDep calc = makeCalc();

        thrown.expect(IllegalArgumentException.class);
        calc.add("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_negative_throws1() throws InterruptedException {
        makeCalc().add("-1");
    }

    @Test
    public void add_multipleNumbers_returnstheSum() throws InterruptedException {
        assertAdding("1,2", 3);
    }

    @Test
    public void add_singleNumber_returnsThatNumber() throws InterruptedException {
        assertAdding("1", 1);
    }


    @Test
    public void add_emptyString_returnsZero() throws InterruptedException {
        assertAdding("", 0);
    }
}