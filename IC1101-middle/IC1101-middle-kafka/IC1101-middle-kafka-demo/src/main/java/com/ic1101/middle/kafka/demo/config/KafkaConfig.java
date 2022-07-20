package com.ic1101.middle.kafka.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

/**
 * @author cwd
 * @date 7/18/22 2:56 PM
 */
@Configuration
public class KafkaConfig {


    @Bean
    @Primary
    public DefaultErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {
        DeadLetterPublishingRecoverer recover = new DeadLetterPublishingRecoverer(template);
        FixedBackOff backOff = new FixedBackOff(10 * 1000L, 3L);
        return new DefaultErrorHandler(recover, backOff);
    }
}
