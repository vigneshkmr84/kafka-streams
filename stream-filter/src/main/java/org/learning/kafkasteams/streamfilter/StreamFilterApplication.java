package org.learning.kafkasteams.streamfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
//@EnableKafka
public class StreamFilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamFilterApplication.class, args);
	}

}
