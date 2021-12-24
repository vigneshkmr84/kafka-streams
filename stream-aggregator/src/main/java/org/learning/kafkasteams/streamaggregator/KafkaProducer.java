package org.learning.kafkasteams.streamaggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    public void produce(String topic, String key, String jsonMessage) {
        kafkaTemplate.send(topic, key, jsonMessage);
    }

}
