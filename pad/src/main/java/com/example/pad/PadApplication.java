package com.example.pad;

import com.example.pad.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class PadApplication {

    public static void main(String[] args) {
        SpringApplication.run(PadApplication.class, args);
    }

}
