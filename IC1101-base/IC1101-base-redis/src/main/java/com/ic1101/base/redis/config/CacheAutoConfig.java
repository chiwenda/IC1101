package com.ic1101.base.redis.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Objects;

/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/5 21:13
 */
@Configuration
@EnableCaching
public class CacheAutoConfig {


    @Bean
    @Primary
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
        CacheProperties.Redis redis = cacheProperties.getRedis();

        if (Objects.nonNull(redis.getTimeToLive())) {
            config = config.entryTtl(redis.getTimeToLive());
        }
        if (Objects.nonNull(redis.getKeyPrefix())) {
            config = config.prefixCacheNameWith(redis.getKeyPrefix());
        }
        if (Objects.nonNull(redis.isCacheNullValues())) {
            config = config.disableCachingNullValues();
        }
        if (Objects.nonNull(redis.isUseKeyPrefix())) {
            config = config.disableKeyPrefix();
        }
        return config;
    }
}
