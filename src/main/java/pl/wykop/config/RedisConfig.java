package pl.wykop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by mariusz on 07.03.17.
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.host}")
    private String hostname;
    @Value("${spring.redis.database}")
    private int database;

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(hostname);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setDatabase(database);
        return jedisConnectionFactory;
    }
}
