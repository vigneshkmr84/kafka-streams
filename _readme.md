


I have used jq command line processor to filter some data from the master json file
``` shell
jq '.[] | select(.imdbRating>6.8 and .imdbRating < 7.1 and .year > 1990)' movies_dump.json > striped_data.json
```

NOTE:  
If the client-id / streams.application-id is changed, Kafka will understand this and will re-play the messages again from first. 


