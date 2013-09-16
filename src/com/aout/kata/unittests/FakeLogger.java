package com.aout.kata.unittests;

import com.aout.kata.Logger;

public class FakeLogger implements Logger {

    public String writtenText;

    public void write(String text) {
        this.writtenText = text;

    }

}
