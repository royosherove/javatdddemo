package com.aout.kata;

public class LoggerImpl implements Logger {

    public void writeMessage(TraceMessage text) {
    }
    public void write(String text) throws InterruptedException {
        Thread.sleep(3000);
    }
}
