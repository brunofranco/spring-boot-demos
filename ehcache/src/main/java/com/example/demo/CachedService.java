package com.example.demo;

import org.springframework.cache.annotation.Cacheable;

public class CachedService {

    @Cacheable("cenas")
    public String cache(Integer i) {
        System.out.println("No cache hit");
        return i.toString();
    }
}
