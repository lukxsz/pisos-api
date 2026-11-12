package br.com.lukas.pisos_api.orcamento;

import java.math.BigDecimal;

public record OrcamentoResponseDTO(
        String linkWhatsapp,
        Long id,
        String nomeCliente,
        String endereco,
        Orcamento.Servico tipoServico,
        Orcamento.Produto tipoProduto,
        Float metragem,
        BigDecimal valor
) {}
