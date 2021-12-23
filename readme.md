# Exploring Kafka Streams

Kafka Streams is one of the ways streaming (flowing events) can be worked upon right away to get insights. Some of the operating that are possible:  
 - Giltering
 - Grouping
 - Aggregating
 - Joining

Kafka Streams is a Java built API. Both Streams & KSQL DB can achieve the same operation - the fundamental difference is that KSQL is an additonal Clustered Infrastructure provisioning where the SQL like queries will run against the topic and perform the desired operation.  Whilst, Streams is a Code level deployment (plugged in with Kafka-Stream Libraries).

<!--- ## Potential Enterprise Use-cases 

    Instead of persisting large data to databases & querying them for real time use cases (which are becoming complicated nowadays, since ingesting sources (producers) keep increasing, as well the existing TerraBytes of Data can post a challenge for performance).
    
    Here comes our Kafka-Streams, where we can start applying filtering logic and take it to next level with state storage. These state storage can be leveraged upon for real-time data (for queries), as well the data can be injected to static database BigData Platform Solutions - where ML / AI / DataScience can be applied. 

    The "REAL-TIME" is the key concept in Kafka-Streams. The world moving so much ahead, where CONSUMERS are not much concerned about yesterday's data. They somehow want real-time data to be processed based on yesterday's performance / outcome.

    Real-Time Spam Filtering, Content Violation in Social Media, Fraudlant Transaction - where more data processing from pre real time.

    Lemme give a more realistic example - have you ever tried to create a duplicate account with just one of (phone number / address the same / credit card number) ? - Most of the Systems do block this kind of behaviour. 
    
>> Events are more valuable to companies than the end state. 
>> Ex: I purchased a coffee at Starbucks, purchased an iPhone 13. Now for a financial institute those individual purchases are events, and the state is mainly the balance that's left after purchase. If they are smart enough, they can process this and start giving me ad's that can lure me to purchase more :) 



    In contrast, if you think why can't a traditional DataBase can't handle the volume - yes, it can handle. But comes with huge costs - for more Enterprise & mission critical applications performance and replication like Oracle RAC + Golden Gate is more reliable. Kafka can do it with the power of distributed computing - that is more resilient and can scale with ease and at a cheaper cost (pennies compared to database). Also, in database the concept is totally different - we don't have much flexiblity to store the events. it's more about the end-state of the transaction. And database is more optimized for query performance - with b-trees powering in backend. So, essentially what we need to undetrstand it that Kafka and DataBases are complementary tools that should be used to solve more complex problems in "REAL-TIME" :) 



  Some interesting thoughts, the Concept of RealTime is more of a myth, where it applied only to the 

  TIBCO offers the most realtime for Enterprise applications - but comes with a huge cost, where companies are moving away to Traditional MQ's - like IBM WebSphere MQ, as well avoiding vendor locks as much as possible


### Kafka High Level Use cases
  The Use cases of Kafka are really interesting from an Enterprise StandPoint. IF a particular data source has to be shared between two applications - either perform SFTP / FTP or IBM Connect-Direct (aka NDM) or Traditional MQ transfer. Kafka simplifies all of these with a simple concept of group-id. it tracks the group id pointers and helps data to be consumed at different rate. IF you look at the bigger picture a single stream of data can be consumed by 4-5 applications, without having massice impact on the performance.  
  

--->


---

## Application Architecure:   

Movie data will be POSTED with Rest-API, that are inserted into movies-dump Topic - ***producer-api*** service will be responsible for this action. 

This topic will be streamed & filtered by ***stream-filter*** service. This service will filter the movies based on the IMDB rating ( good movie if rating >=7; else bad movie) 

Finally, the ***stream-consumer*** service will print the messages in both good-movies & bad-movies topic. One I have used Standard *Kafka Consumer Client library*, while for another I have again used *KStreams* to stream the data

> For each of the service, I have attached reference properties file (sample). I have used Confluent Cloud's infrastructure for this demo, so might be slightly advanced (in terms of security properties).

---


## Inserting JSON Data

The data-dump i have taken contains around 47K movies data. for quick testing I used the JQ filtering to filter out based on year & IMDB Rating. 

``` shell
jq '.[] | select(.imdbRating>6.8 and .imdbRating < 7.1 and .year > 2000 and .year < 2003)' movies_dump.json > striped_data.json
```
