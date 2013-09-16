package com.aout.kata.unittests;

import com.aout.kata.Logger;
import com.aout.kata.StringCalculatorWithOneDep;
import com.aout.kata.StringCalculatorWithTwoDep;
import com.aout.kata.WebService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class StringCalculatorWithTwoDepTestsMockito {


    @Test
    public void add_whenLoggerThrows_callsWebService() throws Throwable {
        Logger stubLogger = mock(Logger.class);
        doThrow(new OutOfMemoryError()).when(stubLogger).write("got 1");

        WebService mockService = mock(WebService.class);
        StringCalculatorWithTwoDep sc = new StringCalculatorWithTwoDep(stubLogger, mockService);

        sc.add("1");

        verify(mockService).write("got java.lang.OutOfMemoryError");
    }

    @Test
    public void add_whenCalled_callsLogger() throws Throwable {
        Logger mockLog = mock(Logger.class);
        StringCalculatorWithTwoDep sc = new StringCalculatorWithTwoDep(mockLog,mock(WebService.class));

        sc.add("1");

        verify(mockLog).write("got 1");
    }
}
