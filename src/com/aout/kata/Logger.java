package com.aout.kata;

import org.mockito.ArgumentCaptor;

public interface Logger {
    public void write(String text) throws Throwable;

    void writeMessage(TraceMessage msg);

}
