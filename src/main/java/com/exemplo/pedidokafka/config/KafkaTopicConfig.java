package com.exemplo.pedidokafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String TOPICO_PEDIDOS = "pedidos";

    @Bean
    public NewTopic topicoPedidos() {
        return TopicBuilder
                .name(TOPICO_PEDIDOS)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
