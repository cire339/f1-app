package com.cire.formula1.config;

import com.cire.formula1.service.DataProcessingService;
import com.cire.formula1.service.DataProcessingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DataProcessingService dataProcessingService() {
        return new DataProcessingServiceImpl();
    }

}
