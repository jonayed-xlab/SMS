package com.jontech.smsserver.service;

import com.jontech.smsserver.config.RedisConfig;
import com.jontech.smsserver.model.Sms;
import com.jontech.smsserver.util.DateTime;
import com.jontech.smsserver.util.Kafka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SmsSenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SmsSenderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Sms send(Sms sms) {

        //send sms to kafka producer
        Boolean kafka = sendToKafka(sms);

        //send sms to redis
        Boolean redis = sendToRedis(sms);

        //send to db

        if (kafka && redis)
            return sms;
        else
            return null;
    }

    private Boolean sendToKafka(Sms sms) {
        if (sendMessage(sms) != "success") {
            return false;
        } else {
            return true;
        }
    }

    private String sendMessage(Sms sms) {
        try {
            kafkaTemplate.send(Kafka.getKafkaTopic(), (DateTime.getDate()+DateTime.getTime()), sms.toString());
            log.info("Kafka send value -> {} " + sms);
        } catch (Exception ex) {
            log.error("Kafka send failed -> ", ex.getCause());
            return "fail";
        }
        return "success";
    }

    private Boolean sendToRedis(Sms sms) {
        Map<String, String> hashMap = new HashMap<>();

        try {
            // Get redis connection
            Jedis jedis = RedisConfig.redisConnection();

            // Send Redis commands
            jedis.hset(getHash(), (getKey()), sms.toString());
            hashMap = jedis.hgetAll(getHash());
            log.info("Redis send value -> {} " + hashMap);

            // Close the Jedis instance
            jedis.close();
        } catch (Exception ex) {
            log.error("Redis send failed -> ", ex.getCause());
            return false;
        }
        return true;
    }

    private static String getHash() {
        return DateTime.getDate();
    }

    private static String getKey() {
        return (DateTime.getDate() + DateTime.getTime());
    }

}
