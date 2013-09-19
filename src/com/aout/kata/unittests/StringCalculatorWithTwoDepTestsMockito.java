package com.aout.kata.unittests;

import com.aout.kata.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class StringCalculatorWithTwoDepTestsMockito {


    @Test
    public void add_whenLoggerThrows_callsWebService() throws Throwable {
        Logger stubLogger = mock(Logger.class);
        doThrow(new OutOfMemoryError()).when(stubLogger).write(anyString());

        WebService mockService = mock(WebService.class);
        StringCalculatorWithTwoDep sc = new StringCalculatorWithTwoDep(stubLogger, mockService);

        sc.add("1");

        verify(mockService).write(contains("OutOfMemoryError"));
    }

    @Test
    public void add_whenCalled_callsLogger() throws Throwable {
        Logger mockLog = mock(Logger.class);
        StringCalculatorWithTwoDep sc = new StringCalculatorWithTwoDep(mockLog,mock(WebService.class));

        sc.add("1");

        verify(mockLog).write("got 1");
    }



    @Test
    public void add_whenCalled_callsLoggerwithObject() throws Throwable {
        Logger mockLog = mock(Logger.class);
        StringCalculatorWithTwoDep sc = new StringCalculatorWithTwoDep(mockLog,mock(WebService.class));

        sc.add("1");

        ArgumentCaptor<TraceMessage> tmArgument = ArgumentCaptor.forClass(TraceMessage.class);

//        verify(mockLog).writeMessage(new TraceMessage(100,"Message"));
//
//        verify(mockLog).writeMessage(tmArgument.capture());
//        TraceMessage gotten = tmArgument.getValue();
//        assertEquals(100,gotten.severity);
//        assertEquals("Messag",gotten.message);
    }
}
