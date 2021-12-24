package org.learning.kafkasteams.streamaggregator;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryController {

    @Autowired
    private final StreamsBuilderFactoryBean factoryBean ;//= new StreamsBuilderFactoryBean();

    @GetMapping("/get/{directorName}")
    public Long director(@PathVariable String directorName){

        KafkaStreams streams = factoryBean.getKafkaStreams();

        ReadOnlyKeyValueStore<String, Long> counts = streams.store(StoreQueryParameters.fromNameAndType("director-count"
                , QueryableStoreTypes.keyValueStore()));

        return counts.get(directorName);
    }
}
