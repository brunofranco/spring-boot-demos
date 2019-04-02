package com.example.influxdb;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InfluxdbApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InfluxdbApplication.class, args);
	}

	@Bean
	public MonitorService monitorService() {
		return new MonitorService();
	}

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}

	@Autowired
	MonitorService monitorService;

	@Autowired
	InfluxMeterRegistry influxMeterRegistry;

	@Override
	public void run(String... args) throws InterruptedException {
		monitorService.test();

		Timer
			.builder("timer")
			.description("a description of what this timer does") // optional
			.tags("region", "test") // optional
			.register(influxMeterRegistry)
			.record(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		System.out.println("Done");
	}
}
