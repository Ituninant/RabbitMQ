package ru.itanton.producer.producers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author itanton
 */
@Component
@Profile("fanout")
@ConditionalOnProperty(value = "producer.send.produce", havingValue = "true")
@RequiredArgsConstructor
@Slf4j
public class RabbitFanoutProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchange.fanout.name}")
    public String fanoutExchangeName;

    private final AtomicInteger i = new AtomicInteger(0);

    @Scheduled(fixedDelayString = "${producer.send.delayMs}")
    public void send() {
        log.info("Send to fanout exchange {}, i={}", fanoutExchangeName, i.incrementAndGet());
        rabbitTemplate.convertAndSend(fanoutExchangeName, "", "fanout message " + i.get());
    }

}
