package ru.itanton.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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

    @Value("${queue.name}")
    public String queueName;

    @Value("${spring.application.name}")
    public String appName;

    @Bean
    Queue queue() {
        return new Queue(queueName + "." + appName);
    }

    @Bean
    @Profile("fanout")
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchangeName);
    }

    @Bean
    @Profile("fanout")
    Binding adminBinding(Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
