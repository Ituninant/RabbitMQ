package ru.itanton.consumer.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author itanton
 */
@Component
@RabbitListener(queues = "${queue.name}" + "." + "${spring.application.name}")
@Slf4j
public class RabbitConsumer {

    @RabbitHandler
    public void receive(String message) {
        log.info("Received message: {}", message);
    }

}
