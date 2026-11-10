package br.com.lukas.pisos_api.orcamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrcamentoRequestDTO(
        @NotBlank String nomeCliente,
        @NotBlank String numeroCelular,
        @NotBlank String endereco,
        @NotNull Orcamento.Servico tipoServico,
        @NotNull @Positive Float metragem
) {}
