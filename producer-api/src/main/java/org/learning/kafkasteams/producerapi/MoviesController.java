package org.learning.kafkasteams.producerapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MoviesController {

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Autowired
    Environment environment;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody Movies movie){

        String producerTopic = environment.getProperty("kafka.producer-topic");
        log.info("Inserting movie - " + movie.toString());

        kafkaProducerService.sendMessage(String.valueOf(movie.getImdbID()), movie, producerTopic);

        return ResponseEntity.accepted().body("Inserted Movie");
    }

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        log.info("Health OK");
        return ResponseEntity.ok().body("OK");
    }
}
