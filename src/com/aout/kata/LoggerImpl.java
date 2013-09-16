package com.aout.kata;

public class LoggerImpl implements Logger {

    public void write(String text) throws InterruptedException {
        Thread.sleep(3000);
    }
}
