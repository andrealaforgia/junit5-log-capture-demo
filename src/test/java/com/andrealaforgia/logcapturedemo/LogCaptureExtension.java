package com.andrealaforgia.logcapturedemo;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.slf4j.LoggerFactory;

public class LogCaptureExtension implements ParameterResolver, BeforeEachCallback, AfterEachCallback {

    private static final String LIST_APPENDER_KEY = "listAppender";
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(LogCaptureExtension.class);
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    @Override
    public void beforeEach(ExtensionContext context) {
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        getStore(context).put(LIST_APPENDER_KEY, listAppender);
        LOGGER.addAppender(listAppender);
        listAppender.start();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        ListAppender<ILoggingEvent> listAppender = getListAppender(context);
        listAppender.stop();
        listAppender.list.clear();
        LOGGER.detachAppender(listAppender);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == LogMessageCapturer.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new LogMessageCapturer(getListAppender(extensionContext));
    }

    private ListAppender<ILoggingEvent> getListAppender(ExtensionContext context) {
        return (ListAppender<ILoggingEvent>) getStore(context).get(LIST_APPENDER_KEY);
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(NAMESPACE);
    }
}
