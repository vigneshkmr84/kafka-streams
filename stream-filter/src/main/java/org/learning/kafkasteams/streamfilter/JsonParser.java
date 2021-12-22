package org.learning.kafkasteams.streamfilter;

import com.google.gson.Gson;

public class JsonParser {

    Gson gson = new Gson();

    public Movies parse(String msg) {
        return gson.fromJson(msg, Movies.class);
    }


    public String objectToString(Movies obj) {
        return gson.toJson(obj);
    }

}
