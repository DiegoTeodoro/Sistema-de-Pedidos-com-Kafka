package com.exemplo.pedidokafka.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PedidoRequest(
        @NotNull(message = "O ID do pedido é obrigatório")
        Long id,

        @NotBlank(message = "O produto é obrigatório")
        String produto,

        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor deve ser maior que zero")
        BigDecimal valor
) {
}
