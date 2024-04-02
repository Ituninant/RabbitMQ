package ru.itanton.producer.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author itanton
 */
@Configuration
public class RabbitQueueConfig {

    @Value("${exchange.fanout.name}")
    public String fanoutExchangeName;

    @Bean
    @Profile("fanout")
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchangeName);
    }

}
