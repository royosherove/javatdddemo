package com.aout.kata.unittests;

import com.aout.kata.Logger;
import com.aout.kata.StringCalculatorWithOneDep;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class StringCalculatorWithOneDepTestsMockito {


    @Test
    public void add_whenCalled_callsLogger() throws Throwable {
        Logger mockLog = mock(Logger.class);
        StringCalculatorWithOneDep sc =
                new StringCalculatorWithOneDep(mockLog);

        sc.add("1");

        verify(mockLog).write(anyString());
    }

}
