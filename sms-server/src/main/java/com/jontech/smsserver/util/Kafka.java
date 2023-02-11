package com.jontech.smsserver.util;

import org.springframework.beans.factory.annotation.Value;

public class Kafka {

//    @Value("${kafka.topic.name}")
    private static String kafkaTopic = "sms-events";

    public static String getKafkaTopic() {
        return kafkaTopic;
    }
}
