package com.andrealaforgia.logcapturedemo;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LogMessageCapturer implements Supplier<List<String>> {

    private final ListAppender<ILoggingEvent> listAppender;

    public LogMessageCapturer(ListAppender<ILoggingEvent> listAppender) {
        this.listAppender = listAppender;
    }

    @Override
    public List<String> get() {
        return listAppender.list.stream().map(ILoggingEvent::getFormattedMessage).collect(Collectors.toList());
    }
}
