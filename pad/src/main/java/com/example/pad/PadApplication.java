package com.example.pad;

import com.example.pad.config.JwtProperties;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class PadApplication {

    public static void main(String[] args) {
        SpringApplication.run(PadApplication.class, args);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
