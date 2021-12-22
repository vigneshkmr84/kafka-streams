# Kafka Stream Processing - Producer API

---

### Description
We insert movie data (from csv) to the REST API, which gets loaded into a Topic. We have a Stream-Service that filters good and bad movies (based on IMDB Rating).
Once this is filtered - they are being published into two (good / bad) new Topics. 


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

#### Sample cURL request
``` shell 
curl -X POST -H 'Content-Type:application/json' -ik "http://localhost:9090/insert" -d '{"imdbID":11833,"title":"From Morn to Midnight","year":1920,"rating":"","runtime":"65 min","genre":"Thriller","released":"","director":"Karl Heinz Martin","writer":"Herbert Juttke, Georg Kaiser (play), Karl Heinz Martin","cast":"Ernst Deutsch, Erna Morena, Roma Bahn, Adolf E. Licho","metacritic":"","imdbRating":6.8,"language":"German","country":"Germany","awards":""}'
```

