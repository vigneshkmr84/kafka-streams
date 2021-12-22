package org.learning.kafkasteams.producerapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class KafkaProducerService {

    JsonParser parser = new JsonParser();
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * @param key       Kafka Key - movie name
     * @param movie     Movie Object (Value)
     * @param topicName Topic Name to insert messages to
     */
    public void sendMessage(String key, Movies movie, String topicName) {
        log.info(String.format("Message will be inserted into Topic - %s", topicName));

        ListenableFuture<SendResult<String, String>> result = this.kafkaTemplate.send(topicName, key, parser.objectToString(movie));


    }
}
