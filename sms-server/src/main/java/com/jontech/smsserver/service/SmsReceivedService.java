package com.jontech.smsserver.service;

import com.jontech.smsserver.config.RedisConfig;
import com.jontech.smsserver.model.Sms;
import com.jontech.smsserver.util.DateTime;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsReceivedService {
    public Sms send(Sms sms) {
        sendToKafka(sms);
        sendToRedis(sms);
        return null;
    }

    private static String sendToKafka(Sms sms){

        return "success";
    }
    private static String sendToRedis(Sms sms){
        Map<String, String> hashMap = new HashMap<>();
        Jedis jedis = RedisConfig.redisConnection();

        // Send Redis commands
        jedis.hset(getHash(), (getKey()), sms.toString());
        hashMap = jedis.hgetAll(getHash());
        System.out.println("Retrieved value: " + hashMap);

        // Close the Jedis instance
        jedis.close();
        return "success";
    }

    private static String getHash(){
        return DateTime.getDate();
    }

    private static String getKey(){
        return (DateTime.getDate()+DateTime.getTime());
    }

}
