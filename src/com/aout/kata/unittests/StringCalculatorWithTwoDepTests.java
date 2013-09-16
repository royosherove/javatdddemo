package com.aout.kata.unittests;

import com.aout.kata.StringCalculatorWithOneDep;
import com.aout.kata.StringCalculatorWithTwoDep;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;


public class StringCalculatorWithTwoDepTests {

    private StringCalculatorWithTwoDep makeCalc() {
        return new StringCalculatorWithTwoDep(new FakeLogger(), new FakeWebService());
    }

    private void assertAdding(String numbers, int expected) throws Throwable {
        StringCalculatorWithTwoDep sc = makeCalc();

        int result = sc.add(numbers);

        assertEquals(expected, result);
    }


    @Test
    public void add_whenLoggerThrows_callsWebService() throws Throwable {
        FakeLogger fakeLogger = new FakeLogger();
        fakeLogger.willThrow(new OutOfMemoryError());

        FakeWebService fakeWebService = new FakeWebService();

        StringCalculatorWithTwoDep sc = new StringCalculatorWithTwoDep(fakeLogger,fakeWebService);

        sc.add("1");

        assertEquals("got OutOfMemoryError", fakeWebService.written);
    }

    @Test
    public void add_whenCalled_callsLogger() throws Throwable {
        FakeLogger mockLog = new FakeLogger();
        StringCalculatorWithOneDep sc = new StringCalculatorWithOneDep(mockLog);

        sc.add("1");

        assertEquals("got 1", mockLog.writtenText);
    }





    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void add_negative_throws2() throws Throwable {
        StringCalculatorWithTwoDep calc = makeCalc();

        thrown.expect(IllegalArgumentException.class);
        calc.add("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_negative_throws1() throws Throwable {
        makeCalc().add("-1");
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
        assertAdding("", 0);
    }
}
