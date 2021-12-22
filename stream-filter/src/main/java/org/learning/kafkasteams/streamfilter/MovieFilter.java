package org.learning.kafkasteams.streamfilter;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Map;
import java.util.function.Function;

//@Configuration
@Slf4j
public class MovieFilter {

    JsonParser parser = new JsonParser();

   /* @Bean
    public KafkaStreamsConfiguration streamsConfig(KafkaProperties kafkaProperties) {
        Map<String, Object> streamsProperties = kafkaProperties.buildStreamsProperties();
        streamsProperties.put(BOOTSTRAP_SERVERS_CONFIG, server);
        streamsProperties.put(APPLICATION_ID_CONFIG, applicationId);
        streamsProperties.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsProperties.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return new KafkaStreamsConfiguration(streamsProperties);
    }*/

    //@Bean
    public Function<KStream<String, String>, KStream<String, String>> movieFilterBean(){

        return kStream -> kStream.filter( (key, jsonMovie) ->{
            Movies movie = parser.parse(jsonMovie);

            if (movie.getImdbRating() >=7 ){
                log.info("Movie %s is a good movie", movie.getTitle() );
            }else{
                log.info("Movie %s is not a good movie", movie.getTitle() );
            }
            return movie.getImdbRating() >=7;
        });
    }
}
