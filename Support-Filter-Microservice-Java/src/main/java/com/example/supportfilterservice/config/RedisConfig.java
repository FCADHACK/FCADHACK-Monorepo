package com.example.supportfilterservice.config;

import com.example.supportfilterservice.domain.DTO.Endpoint;
import com.example.supportfilterservice.domain.DTO.RegexConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.List;

@Configuration
public class RedisConfig {
    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("redis", 6379); // 'redis' - имя вашего Redis сервиса в Docker Compose
    }

    @Bean
    public RedisTemplate<String, List<Endpoint>> endpointRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, List<Endpoint>> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
    @Bean
    public RedisTemplate<String, List<RegexConfig>> regexConfigRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, List<RegexConfig>> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
    @Bean
    public ChannelTopic disabledEndpointsUpdatesTopic() {
        return new ChannelTopic("disabledEndpointsUpdates"); // Название канала
    }

    @Bean
    public ChannelTopic regexConfigUpdatesTopic() {
        return new ChannelTopic("regexConfigUpdates"); // Название канала для regexConfig
    }
}
