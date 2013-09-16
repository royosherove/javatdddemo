package com.aout.kata.unittests;

import com.aout.kata.Logger;

public class FakeLogger implements Logger {

    public String writtenText;
    private Throwable toThrow;

    public void write(String text) throws Throwable {
        this.writtenText = text;
        if (toThrow != null) {
            throw toThrow;
        }

    }

    public void willThrow(Throwable throwable) {
        toThrow = throwable;
    }
}
