package org.project.kafka.utils;

import com.google.gson.Gson;
import org.project.kafka.model.Order;

public class JsonParser {

    Gson gson = new Gson();

    public Order parse(String msg) {
        return gson.fromJson(msg, Order.class);
    }


    public String objectToString(Order obj) {
        return gson.toJson(obj);
    }

}
