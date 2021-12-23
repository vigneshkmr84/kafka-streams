package org.learning.kafkasteams.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    JsonParser parser = new JsonParser();

    @KafkaListener(topics = "bad-movies", groupId = "standard-kafka-consumer")
    public void consumerMessage(@Payload String message) {
        Movies badMovie = parser.parse(message);

        log.info("Standard Kafka Consumer Bad Movie - " + badMovie.toString());
    }
}
