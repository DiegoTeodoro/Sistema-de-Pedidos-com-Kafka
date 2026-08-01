package com.exemplo.pedidokafka.producer;

import com.exemplo.pedidokafka.config.KafkaTopicConfig;
import com.exemplo.pedidokafka.dto.PedidoRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PedidoProducer {

    private final KafkaTemplate<String, PedidoRequest> kafkaTemplate;

    public PedidoProducer(KafkaTemplate<String, PedidoRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<Void> enviar(PedidoRequest pedido) {
        String chave = pedido.id().toString();

        return kafkaTemplate
                .send(KafkaTopicConfig.TOPICO_PEDIDOS, chave, pedido)
                .thenAccept(resultado -> System.out.printf(
                        "Producer enviou o pedido %s para o tópico %s, partição %d, offset %d%n",
                        pedido.id(),
                        resultado.getRecordMetadata().topic(),
                        resultado.getRecordMetadata().partition(),
                        resultado.getRecordMetadata().offset()
                ));
    }
}
