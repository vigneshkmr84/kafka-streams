package org.project.kafka;

import java.util.UUID;

public class Temp {

    public static void main(String[] args){
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println("uuid = " + uuid);
    }
}
