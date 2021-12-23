package org.learning.kafkasteams.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
public class StreamConsumer {


    @Autowired
    Environment environment;

    JsonParser parser = new JsonParser();

    @Autowired
    public void process(StreamsBuilder builder) {

        final String inputTopic = environment.getProperty("kafka.stream.topic1");
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();
        final Serde<Integer> integerSerde = Serdes.Integer();


        KStream<String, String> kStream = builder.stream(inputTopic, Consumed.with(stringSerde, stringSerde));

        kStream.filter((key, jsonMovie) -> {
            Movies movie = parser.parse(jsonMovie);

            log.info("Stream Consumer : [" + movie.getTitle() + "] is a good movie");
            log.info(movie.toString());

            /*if (movie.getImdbRating() >= 7) {
                log.info("Movie [" + movie.getTitle() + "] is a good movie");

                //kafkaProducer.produce("good-movies", String.valueOf(movie.getImdbID()), jsonMovie);
            } else {
                log.info("Movie [" + movie.getTitle() + "] is not a good movie");
                //kafkaProducer.produce("bad-movies", String.valueOf(movie.getImdbID()), jsonMovie);
            }
            return movie.getImdbRating() >= 7;*/
            return true;
        });
    }
}
