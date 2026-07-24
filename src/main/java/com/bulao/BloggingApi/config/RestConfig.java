package com.bulao.BloggingApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

}
