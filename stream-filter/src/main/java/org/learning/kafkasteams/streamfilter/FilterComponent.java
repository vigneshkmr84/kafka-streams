package org.learning.kafkasteams.streamfilter;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FilterComponent {

    JsonParser parser = new JsonParser();


    @Autowired
    public void process(StreamsBuilder builder){

        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();
        final Serde<Integer> integerSerde = Serdes.Integer();


        KStream<String, String> kStream = builder.stream("movies-dump", Consumed.with(stringSerde, stringSerde));

        kStream.filter( (key, jsonMovie) ->{
            Movies movie = parser.parse(jsonMovie);

            if (movie.getImdbRating() >=7 ){
                log.info("Movie [" + movie.getTitle() +  "] is a good movie" );
            }else{
                log.info("Movie [" + movie.getTitle() +  "] is not a good movie" );
            }
            return movie.getImdbRating() >=7;
        });
    }

}
