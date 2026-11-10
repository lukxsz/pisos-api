package br.com.lukas.pisos_api.orcamento;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrcamentoService{
    private final OrcamentoRepository orcamentoRepository;

    public OrcamentoService(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    public Orcamento criarOrcamento(OrcamentoRequestDTO dados){
        Orcamento orcamento = new Orcamento();
        orcamento.setNomeCliente(dados.nomeCliente());
        orcamento.setNumeroCelular(dados.numeroCelular());
        orcamento.setEndereco(dados.endereco());
        orcamento.setTipoServico(dados.tipoServico());
        orcamento.setMetragem(dados.metragem());

        orcamento.setStatusOrcamento(Orcamento.StatusOrcamento.PENDENTE);

        double valor = tabelaPreco(orcamento.getTipoServico());
        BigDecimal valorFinal = BigDecimal.valueOf((valor*orcamento.getMetragem()));
        orcamento.setValor(valorFinal);

        return orcamentoRepository.save(orcamento);
    }

    public List<Orcamento> listarOrcamentos(){
        return orcamentoRepository.findAll();
    }

    public double tabelaPreco(Orcamento.Servico servico){
        double precoServico = 0;
        switch (servico){
            case RASPAGEM :
                precoServico = 20.00;
                break;//TODO: pegar valor reais dos servicos
            case APLICACAO:
                precoServico = 10.00;
                break;
            case COLOCACAO:
                precoServico = 11.00;
                break;
        }
        return precoServico;
    }
}
