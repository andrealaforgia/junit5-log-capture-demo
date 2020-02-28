package com.andrealaforgia.logcapturedemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleLoggingClass {

    public void doSomething() {
        log.info("some info");
        log.warn("some warning");
        log.error("some error");
    }
}
