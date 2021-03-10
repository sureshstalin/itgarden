package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource(value = "classpath:training-app.yml", factory = YamlPropertySourceFactory.class)
public class TrainingAppConfig {
}
