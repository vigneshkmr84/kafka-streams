# Kafka Stream Consumer Service

### Description

This will showcase both the methods of stream consumption.  
1. Using normal Kafka Consumption ( ``` @Listener ``` method) - I have consumed messages from ***bad-movies*** topic in this way
2. Consumption again as Stream itself (``` @Autowired StreamsBuilder``` ) - ***good-movies*** topics are consumed with this pattern


The above can be split into two different services, but I wanted to try out how it looks if same service is being used (sometimes we might need this scenario)

#### Sample JSON Data

```json
{
  "imdbID": 11833,
  "title": "From Morn to Midnight",
  "year": 1920,
  "rating": "",
  "runtime": "65 min",
  "genre": "Thriller",
  "released": "",
  "director": "Karl Heinz Martin",
  "writer": "Herbert Juttke, Georg Kaiser (play), Karl Heinz Martin",
  "cast": "Ernst Deutsch, Erna Morena, Roma Bahn, Adolf E. Licho",
  "metacritic": "",
  "imdbRating": 6.8,
  "language": "German",
  "country": "Germany",
  "awards": ""
}
```

