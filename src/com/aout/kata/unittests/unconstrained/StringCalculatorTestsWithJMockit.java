package com.aout.kata.unittests.unconstrained;

import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.junit.Test;

import static org.mockito.Matchers.anyString;

public class StringCalculatorTestsWithJMockit {

    @Test
    public void add_always_callsStaticLogger() throws Throwable {

        new NonStrictExpectations() {
           StaticLogger logger;
            {
                StaticLogger.write(anyString);

            }};

        StringCalculatorWithStatics sc =
                new StringCalculatorWithStatics();

        sc.add("1");

        new Verifications(){{
            StaticLogger.write(withSubstring("got 1"));
        }};
    }

    @Test
    public void add_loggerThrows_callsStaticWebService() throws Throwable {

        new NonStrictExpectations() {
           StaticLogger logger;
            {
                StaticLogger.write(anyString);
                result = new OutOfMemoryError();
            }
            StaticWebService service;
            {
                StaticWebService.notify(anyString);
            }
        };

        StringCalculatorWithStatics sc =
                new StringCalculatorWithStatics();

        sc.add("1");

        new Verifications(){{
            StaticWebService.notify(withSubstring("got error"));
        }};
    }
}

