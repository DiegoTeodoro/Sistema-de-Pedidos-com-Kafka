package com.exemplo.pedidokafka.controller;

import com.exemplo.pedidokafka.dto.PedidoRequest;
import com.exemplo.pedidokafka.producer.PedidoProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoProducer pedidoProducer;

    public PedidoController(PedidoProducer pedidoProducer) {
        this.pedidoProducer = pedidoProducer;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(@Valid @RequestBody PedidoRequest pedido) {
        pedidoProducer.enviar(pedido);

        Map<String, Object> resposta = Map.of(
                "mensagem", "Pedido enviado para o Kafka",
                "pedido", pedido
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resposta);
    }
}
