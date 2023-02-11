package com.jontech.smsserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

    @Bean
    public static Jedis redisConnection() {
        Jedis jedis = new Jedis("localhost", 6666);
        return jedis;
    }



}
