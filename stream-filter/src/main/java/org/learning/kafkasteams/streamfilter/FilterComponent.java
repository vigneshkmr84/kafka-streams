package org.learning.kafkasteams.streamfilter;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FilterComponent {

    JsonParser parser = new JsonParser();

    @Autowired
    Environment environment;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public void process(StreamsBuilder builder){

        final String inputTopic = environment.getProperty("kafka.stream.topic");
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();
        final Serde<Integer> integerSerde = Serdes.Integer();



        KStream<String, String> kStream = builder.stream(inputTopic, Consumed.with(stringSerde, stringSerde));

        kStream.filter( (key, jsonMovie) ->{
            Movies movie = parser.parse(jsonMovie);

            if (movie.getImdbRating() >=7 ){
                log.info("Movie [" + movie.getTitle() +  "] is a good movie" );

                kafkaTemplate.send("good-movies", String.valueOf(movie.getImdbID()), jsonMovie);
            }else{
                log.info("Movie [" + movie.getTitle() +  "] is not a good movie" );
                kafkaTemplate.send("bad-movies", String.valueOf(movie.getImdbID()), jsonMovie);
            }
            return movie.getImdbRating() >=7;
        });
    }

}
