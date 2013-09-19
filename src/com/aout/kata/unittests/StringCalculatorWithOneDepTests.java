package com.aout.kata.unittests;

import com.aout.kata.Logger;
import com.aout.kata.StringCalculator;
import com.aout.kata.StringCalculatorWithOneDep;
import com.aout.kata.TraceMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;


public class StringCalculatorWithOneDepTests {


    class MyFakeLogger implements Logger {
        public String written;

        public void writeMessage(TraceMessage text) {
        }
        public void write(String text) {
            written = text;
        }
    }


    @Test
    public void add_whenCalled_callsLogger() throws Throwable {
        MyFakeLogger mockLog = new MyFakeLogger();
        StringCalculatorWithOneDep sc = new StringCalculatorWithOneDep(mockLog);

        sc.add("1");

        assertEquals("got 1", mockLog.written);
    }

}
