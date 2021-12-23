# Exploring Kafka Streams

Kafka Streams is one of the ways streaming (flowing data) can be worked upon right away to get insights. Some of the operating that are possible:  
 - filtering
 - grouping
 - aggregating
 - joining

Kafka Streams is a Java built API. Both Streams & KSQL DB can achieve the same operation - the fundamental difference is that KSQL is an additonal Clustered Infrastructure provisioning where the SQL like queries will run against the topic and perform the desired operation.  Whilst, Streams is a Code level deployment (plugged in with Kafka-Stream Libraries).

---

## Application Architecure:   

Movie data will be POSTED with Rest-API, that are inserted into movies-dump Topic - ***producer-api*** service will be responsible for this action. 

This topic will be streamed & filtered by ***stream-filter*** service. This service will filter the movies based on the IMDB rating ( good movie if rating >=7; else bad movie) 

Finally, the ***stream-consumer*** service will print the messages in both good-movies & bad-movies topic. One I have used Standard *Kafka Consumer Client library*, while for another I have again used *KStreams* to stream the data

---

For each of the service, I have attached reference properties file (sample). I have used Confluent Cloud's infrastructure for this demo, so might be slightly advanced (in terms of security properties).

Inserting JSON Data: 

The data-dump i have taken contains around 47K movies data. for quick testing I used the JQ filtering to filter out based on year & IMDB Rating. 
``` shell
jq '.[] | select(.imdbRating>6.8 and .imdbRating < 7.1 and .year > 2000 and .year < 2003)' movies_dump.json > striped_data.json
```