package ru.kranbe.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {
    private String host;
    private int port;
    private String username;
    private String password;
}
