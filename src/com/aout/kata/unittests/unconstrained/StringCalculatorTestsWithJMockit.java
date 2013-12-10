package com.aout.kata.unittests.unconstrained;

import mockit.*;
import org.junit.*;

public class StringCalculatorTestsWithJMockit {

    @Tested StringCalculatorWithStatics sc;
    @Mocked StaticLogger logger;

    @Test
    public void add_always_callsStaticLogger() throws Throwable {

        sc.add("1");

        new Verifications() {{
            StaticLogger.write(withSubstring("got 1"));
        }};
    }

    @Test
    public void add_loggerThrows_callsStaticWebService(@Mocked StaticWebService service) throws Throwable {

        new NonStrictExpectations() {{
            StaticLogger.write(anyString);
            result = new OutOfMemoryError();
        }};

        sc.add("1");

        new Verifications() {{
            StaticWebService.notify(withSubstring("got error"));
        }};
    }
}

