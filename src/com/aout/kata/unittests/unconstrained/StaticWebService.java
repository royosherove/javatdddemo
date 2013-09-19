package com.aout.kata.unittests.unconstrained;

public class StaticWebService {
    public static void notify(String text) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
