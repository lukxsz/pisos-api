package br.com.lukas.pisos_api.orcamento;

import br.com.lukas.pisos_api.preco.Preco;
import br.com.lukas.pisos_api.preco.PrecoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class OrcamentoService{
    @Value("${app.whatsapp.numero}")
    private String numeroDestino;

    private final OrcamentoRepository orcamentoRepository;
    private final PrecoRepository precoRepository;

    public OrcamentoService(OrcamentoRepository orcamentoRepository, PrecoRepository precoRepository) {
        this.orcamentoRepository = orcamentoRepository;
        this.precoRepository = precoRepository;
    }

    public OrcamentoResponseDTO criarOrcamento(OrcamentoRequestDTO dados){
        Orcamento orcamento = new Orcamento();
        orcamento.setNomeCliente(dados.nomeCliente());
        orcamento.setNumeroCelular(dados.numeroCelular());
        orcamento.setEndereco(dados.endereco());
        orcamento.setTipoServico(dados.tipoServico());
        orcamento.setMetragem(dados.metragem());
        orcamento.setTipoProduto(dados.tipoProduto());
        orcamento.setStatusOrcamento(Orcamento.StatusOrcamento.PENDENTE);

        double valorServico = tabelaPrecoServico(orcamento.getTipoServico());
        double valorProduto = tabelaPrecoProduto(orcamento.getTipoProduto());

        BigDecimal valorFinal = BigDecimal.valueOf((valorServico+valorProduto)*orcamento.getMetragem());
        orcamento.setValor(valorFinal);

        Orcamento orcamentoSalvo = orcamentoRepository.save(orcamento);
        String link = gerarLinkWhatsapp(orcamentoSalvo);
        return new OrcamentoResponseDTO (
                link,
                orcamentoSalvo.getId(),
                orcamentoSalvo.getNomeCliente(),
                orcamentoSalvo.getEndereco(),
                orcamentoSalvo.getTipoServico(),
                orcamentoSalvo.getTipoProduto(),
                orcamentoSalvo.getMetragem(),
                orcamentoSalvo.getValor()
        );

    }

    public List<Orcamento> listarOrcamentos(){
        return orcamentoRepository.findAll();
    }

    public Orcamento atualizarStatus(Long id, Orcamento.StatusOrcamento novoStatus){
        Orcamento orcamento = orcamentoRepository.findById(id).orElseThrow();
        orcamento.setStatusOrcamento(novoStatus);
        return orcamentoRepository.save(orcamento);
    }

    public Orcamento buscarOrcamento(Long id){
        return orcamentoRepository.findById(id).orElseThrow();
    }

    public void deletarOrcamento(Long id){
        orcamentoRepository.deleteById(id);
    }

    public String gerarLinkWhatsapp(Orcamento orcamento){

        String mensagemWhatsapp = "Olá! Me chamo " + orcamento.getNomeCliente()
                + ", fiz o orçamento #" + orcamento.getId()
                + " (" + orcamento.getTipoServico() + " com " + orcamento.getTipoProduto()
                + " para " + orcamento.getMetragem() + "m² - Valor estimado: R$ " + orcamento.getValor()
                + ") e gostaria de agendar uma visita!";

        String mensagemURL = URLEncoder.encode(mensagemWhatsapp, StandardCharsets.UTF_8);

        return  "https://wa.me/" + numeroDestino + "?text=" + mensagemURL;
    }

    public Preco obterTabelaPreco(){
        return precoRepository.findById(1L).orElseGet(() -> precoRepository.save(new Preco()));
    }
    public Preco atualizarTabelaPreco(Preco novosPrecos){
        novosPrecos.setId(1L);
        return precoRepository.save(novosPrecos);
    }
    public double tabelaPrecoServico(Orcamento.Servico servico){
        double precoServico = 0;
        Preco preco = obterTabelaPreco();
        switch (servico){
            case RASPAGEM :
                precoServico = preco.getPrecoRaspagem().doubleValue();
                break;//TODO: pegar valor reais dos servicos
            case APLICACAO:
                precoServico = preco.getPrecoAplicacao().doubleValue();
                break;
            case COLOCACAO:
                precoServico = preco.getPrecoColocacao().doubleValue();
                break;
        }
        return precoServico;
    }
    public double tabelaPrecoProduto(Orcamento.Produto produto) {
        double precoProduto = 0;
        Preco preco = obterTabelaPreco();
        switch (produto){
            case BONA:
                precoProduto = preco.getPrecoBona().doubleValue();
                break;
            case SINTEKO:
                precoProduto = preco.getPrecoSinteko().doubleValue();
                break;
            case CASCOLAC:
                precoProduto =preco.getPrecoCascolac().doubleValue();
                break;
            case NENHUM:
                precoProduto = 0;
                break;
        }
        return precoProduto;
    }
}
