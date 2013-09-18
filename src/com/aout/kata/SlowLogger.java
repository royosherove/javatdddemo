package com.aout.kata;

public class SlowLogger {
    public void write(String text) throws Throwable{
       Thread.sleep(10000);
    }
}
