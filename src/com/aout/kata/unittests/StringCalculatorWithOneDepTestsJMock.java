package com.aout.kata.unittests;

import com.aout.kata.Logger;
import com.aout.kata.StringCalculatorWithOneDep;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;


public class StringCalculatorWithOneDepTestsJMock {


    @Test
    public void add_whenCalled_callsLogger() throws Throwable {
        Mockery context = new Mockery();
        final Logger mockLog = context.mock(Logger.class);
        context.checking(new Expectations(){{
            allowing(mockLog).write(with(any(String.class)));
        }
        });

        StringCalculatorWithOneDep sc =
                new StringCalculatorWithOneDep(mockLog);

        sc.add("1"); //BANG!
    }

}
