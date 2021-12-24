package org.learning.kafkasteams.streamaggregator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringSerializer;
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
    private final StreamsBuilderFactoryBean factoryBean ;//= new StreamsBuilderFactoryBean();

    @GetMapping("/get/{directorName}")
    public ResponseEntity<Long> director(@PathVariable String directorName
        , HttpServletRequest request){

        // log.info("Requested url : " + request.getRequestURI());
        log.info("Query for Director : " + directorName);
        KafkaStreams streams = factoryBean.getKafkaStreams();

        ReadOnlyKeyValueStore<String, Long> counts = getStores(streams);
        return ResponseEntity.ok().body(counts.get(directorName));
    }


    public ReadOnlyKeyValueStore<String, Long> getStores(KafkaStreams streams){
        return streams.store(
                StoreQueryParameters.fromNameAndType("director-count"
                        , QueryableStoreTypes.keyValueStore()));
    }
    @GetMapping("/health")
    public ResponseEntity<String> health(){
        //factoryBean.getKafkaStreams().state().isRunningOrRebalancing()
        if ( !factoryBean.getKafkaStreams().state().equals(KafkaStreams.State.RUNNING) ){
            log.info("Health-OK");
            return ResponseEntity.ok().body("OK");
        }else
            return ResponseEntity.badRequest().body("Stream not yet started");

    }
}
