package br.com.lukas.pisos_api.orcamento;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nomeCliente;
    private String numeroCelular;
    private String endereco;
    private Float metragem;
    private BigDecimal valor;

    public enum Servico {
        RASPAGEM,
        APLICACAO,
        COLOCACAO
    }
    @Enumerated(EnumType.STRING)
    private Servico tipoServico;

    public enum StatusOrcamento{
         PENDENTE,
         APROVADO,
         EM_ANDAMENTO,
         FINALIZADO
    }
    @Enumerated(EnumType.STRING)
    private StatusOrcamento statusOrcamento;

    public enum Produto {
        BONA,
        SINTEKO,
        CASCOLAC,
        NENHUM                                  //TODO: perguntar as opcoes de resina
    }
    @Enumerated(EnumType.STRING)
    private Produto tipoProduto;

    public Produto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(Produto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Float getMetragem() {
        return metragem;
    }

    public void setMetragem(Float metragem) {
        this.metragem = metragem;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Servico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Servico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public StatusOrcamento getStatusOrcamento() {
        return statusOrcamento;
    }

    public void setStatusOrcamento(StatusOrcamento statusOrcamento) {
        this.statusOrcamento = statusOrcamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
