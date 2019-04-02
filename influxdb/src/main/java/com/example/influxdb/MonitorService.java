package com.example.influxdb;

import io.micrometer.core.annotation.Timed;

public class MonitorService {

    @Timed(value = "test")
    public void test() throws InterruptedException {
        Thread.sleep(1999);
    }
}
