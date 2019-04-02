package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CachedService cachedService() {
		return new CachedService();
	}

	@Autowired
	CacheManager cacheManager;

	@Autowired
	CachedService cachedService;

	@Override
	public void run(String... args) throws InterruptedException {
		System.out.println(cacheManager);
		cachedService.cache(1);
		cachedService.cache(1);
		Thread.sleep(5000);
		cachedService.cache(1);
	}
}
