package io.bootify.l10_minor_project.config;

import io.bootify.l10_minor_project.domain.Visit;
import io.bootify.l10_minor_project.model.PendingVisitsDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
public class RedisConfig  {


    @Bean
    public RedisTemplate<String, PendingVisitsDto> employeeRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, PendingVisitsDto> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new Jackson2JsonRedisSerializer<PendingVisitsDto>(PendingVisitsDto.class));
        return template;
    }
}
