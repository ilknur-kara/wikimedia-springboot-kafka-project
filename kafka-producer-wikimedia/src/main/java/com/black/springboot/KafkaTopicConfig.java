package com.black.springboot;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

//Using configuration annotation to make this class as a configuration Java class.
@Configuration
public class KafkaTopicConfig {

    //Created a topic for kafka.
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("wikimedia_recentchange")
                .build();
    }
}
