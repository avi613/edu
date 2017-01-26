package com.talan.coin.config;

import com.talan.coin.ethereum.api.EthereumTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EthereumConfig {
    @Bean
    public EthereumTemplate ethereumTemplate(RestTemplateBuilder builder) {
        return new EthereumTemplate("localhost", "8545", builder.build());
    }
}
