package org.project.kafka.controller;

import org.project.kafka.model.Order;
import org.project.kafka.utils.JsonParser;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

// This is a Sample Class - Will be moved out
@Service
public class Consumer {

    JsonParser jsonParser = new JsonParser();

    //@KafkaListener(topics = "test-topic", groupId = "demo-cluster")
    //, containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload String message
            , @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key
            , @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long receivedTimeStamp) {
        //,@Header(KafkaHeaders.ORIGINAL_TIMESTAMP) long originalTimeStamp) {

        System.out.println("Received Message Key : " + key);
        System.out.println("Received Message timestamp : " + receivedTimeStamp);
        //System.out.println("Original Message timestamp : " + originalTimeStamp );

        Order incomingOrder = jsonParser.parse(message);
        incomingOrder.setProcessedTimeStamp(new Date());
        System.out.println("Incoming message : " + incomingOrder.toString());

        // need to insert the messages to redis service
        // Put the message to Next Service Topic
    }

}
