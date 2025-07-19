package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {
        "org.example.proxy", "org.example.service", "org.example.repository"})
public class ProjectConfig {

}
