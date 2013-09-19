package com.aout.kata;

public class TraceMessage {
    public final int severity;
    public final String message;

    public TraceMessage(int severity, String message) {
        this.severity = severity;

        this.message = message;
    }
}
