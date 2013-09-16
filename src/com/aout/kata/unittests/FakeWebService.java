package com.aout.kata.unittests;

import com.aout.kata.WebService;

public class FakeWebService implements WebService {

    public String written;

    public void write(String text) {
        written = text;
    }
}
