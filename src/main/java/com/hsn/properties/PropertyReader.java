package com.hsn.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertyReader {
    @Autowired
    Environment environment;
    public String getProperty(String key) {
        return environment.getProperty(key);
    }
}
