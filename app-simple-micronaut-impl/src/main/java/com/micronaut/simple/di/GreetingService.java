package com.micronaut.simple.di;

import jakarta.inject.Singleton;

@Singleton
public class GreetingService {

    public String greeting(String name) {
        return "Hello "+name+", Welcome.";
    }
}
