package com.andrealaforgia.logcapturedemo;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(LogCaptureExtension.class)
class SampleLoggingClassTest {

    private LogMessageCapturer logMessageCapturer;

    @BeforeEach
    public void beforeEach(LogMessageCapturer logMessageCapturer) {
        this.logMessageCapturer = logMessageCapturer;
    }

    @Test
    public void shouldLogAsExpected() {
        SampleLoggingClass sampleLoggingClass = new SampleLoggingClass();

        sampleLoggingClass.doSomething();

        assertThat(logMessageCapturer.get()).containsAll(
                ImmutableSet.of(
                        "some info",
                        "some warning",
                        "some error"
                )
        );
    }
}
