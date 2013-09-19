package com.aout.kata.unittests.unconstrained;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StaticLogger.class ,
                StaticWebService.class})
public class StringCalculatorTestsWithPowermock {
    @Test
    public void add_always_callsStaticLogger() throws Throwable {
        PowerMockito.mockStatic(StaticLogger.class);

        StringCalculatorWithStatics sc =
                new StringCalculatorWithStatics();

        sc.add("1");

        PowerMockito.verifyStatic();
        StaticLogger.write(anyString());
    }

    @Test
    public void add_whenLoggerThrows_callsStaticLogger() throws Throwable {
        PowerMockito.mockStatic(StaticLogger.class);

        PowerMockito.doThrow(new OutOfMemoryError())
                .when(StaticLogger.class);
                 StaticLogger.write(anyString());


        StringCalculatorWithStatics sc =
                new StringCalculatorWithStatics();

        sc.add("1");

        PowerMockito.verifyStatic();
        StaticLogger.write(anyString());
    }
}

