package org.learning.kafkasteams.streamaggregator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QueryController {

    @Autowired
    private final StreamsBuilderFactoryBean factoryBean ;

    @GetMapping("/get/{directorName}")
    public ResponseEntity<Long> director(@PathVariable String directorName){

        // log.info("Requested url : " + request.getRequestURI());
        log.info("Query for Director : " + directorName);
        KafkaStreams streams = factoryBean.getKafkaStreams();

        ReadOnlyKeyValueStore<String, Long> counts = streams.store(
                StoreQueryParameters.fromNameAndType("director-count"
                        , QueryableStoreTypes.keyValueStore()));

        return ResponseEntity.ok().body(counts.get(directorName));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<Long> yearQuery(@PathVariable Integer year){

        log.info("Query for year : " + year);
        KafkaStreams streams = factoryBean.getKafkaStreams();

        ReadOnlyKeyValueStore<Integer, Long> counts = streams.store(
                StoreQueryParameters.fromNameAndType("year-count"
                        , QueryableStoreTypes.keyValueStore()));

        // UnComment the below Code to Iterate and print all the values in the state-store
        /*KeyValueIterator<Integer, Long> iterator = counts.all();
        while (iterator.hasNext()){

            KeyValue<Integer, Long> i = iterator.next();
            log.info("key : " + i.key + "; value : " + i.value);
        }*/

        log.info("State store - "  + streams.state().toString());

        if ( streams.state().toString().equalsIgnoreCase("RUNNING") ) {
            Long output = counts.get(year);
            return ResponseEntity.ok().body( output == 0? 0L : output);
        }
        else
            return ResponseEntity.ok().body( 0L);
    }


    @GetMapping("/health")
    public ResponseEntity<String> health(){

        if ( !factoryBean.getKafkaStreams().state().equals(KafkaStreams.State.RUNNING) ){
            log.info("Health-OK");
            return ResponseEntity.ok().body("OK");
        }else
            return ResponseEntity.badRequest().body("Stream not yet started");

    }
}
