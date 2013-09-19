package com.aout.kata.unittests;

import com.aout.kata.Logger;
import com.aout.kata.StringCalculatorWithOneDep;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.replay;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class StringCalculatorWithOneDepTestsEasyMock {


    @Test
    public void add_whenCalled_callsLogger() throws Throwable {

        Logger mockLog = createNiceMock(Logger.class);

        StringCalculatorWithOneDep sc = new StringCalculatorWithOneDep(mockLog);

        replay(mockLog);
        sc.add("1");
    }

}
