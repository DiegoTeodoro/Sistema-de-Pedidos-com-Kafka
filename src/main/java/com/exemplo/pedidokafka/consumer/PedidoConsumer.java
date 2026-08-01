package com.exemplo.pedidokafka.consumer;

import com.exemplo.pedidokafka.config.KafkaTopicConfig;
import com.exemplo.pedidokafka.dto.PedidoRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoConsumer {

    @KafkaListener(topics = KafkaTopicConfig.TOPICO_PEDIDOS, groupId = "grupo-pedidos")
    public void receber(PedidoRequest pedido) {
        System.out.println("========================================");
        System.out.println("Consumer recebeu um pedido do Kafka");
        System.out.println("ID: " + pedido.id());
        System.out.println("Produto: " + pedido.produto());
        System.out.println("Valor: R$ " + pedido.valor());
        System.out.println("========================================");
    }
}
