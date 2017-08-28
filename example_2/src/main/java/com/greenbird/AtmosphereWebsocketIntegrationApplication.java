package com.greenbird;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AtmosphereWebsocketIntegrationApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(AtmosphereWebsocketIntegrationApplication.class, args);
    }
}
