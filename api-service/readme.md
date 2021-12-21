# Confluent Kafka + Spring Boot Service

I used Confluent Cloud to set up and work on Kafka - quick and easy. Some pointers to be noted.

- For starters Comment out the Schema Registry properties, provided by confluent
- Group ID for the @KafkaLister Annotation : (Cluster Name)
- spring.kafka.producer.key-deserializer & spring.kafka.producer.value-deserializer - these are relevant to the message
  data types.
    - Reference
      - [More Custom Deserializer options](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web.servlet.spring-mvc.json)

---

### Basic Confluent CLI Usage

List available Clusters

```shell
confluent kafka cluster list
```

List Available Topics

```shell
confluent kafka topic list
```

Creating a Topic

 ```shell
  confluent kafka topic create test-topic 
 ```

Ingesting messages (Producer-mode) - start entering the messages

```shell
confluent kafka topic produce test-topic --parse-key --delimiter
```

## Optimizations

There are multiple ways of de-serializing the messages.

1. Simply accept them as String and then convert them to POJO with libraries (like GSON)
2. Write custom config class - to deserialize them and accept them as POJO within the listener block.

Both has its own pros and cons. But, I preferred the first option - since it was easy and out of the box implementation

Sample JSON Message :

```json
{
  "id": 1,
  "first_name": "Elaine",
  "last_name": "Dallosso",
  "email": "edallosso0@networkadvertising.org",
  "product": "Soup Campbells Beef With Veg",
  "address": "575 Portage Hill",
  "phone_number": "659-993-4818",
  "count": 17,
  "cost": 21.38,
  "currency": "USD"
}
```
