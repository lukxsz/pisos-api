package br.com.lukas.pisos_api.preco;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Preco {
    @Id
    private Long id = 1L;
    private BigDecimal precoRaspagem = BigDecimal.valueOf(20.00);
    private BigDecimal precoAplicacao = BigDecimal.valueOf(10.00);
    private BigDecimal precoColocacao = BigDecimal.valueOf(11.00);
    private BigDecimal precoBona = BigDecimal.valueOf(20.00);
    private BigDecimal precoSinteko = BigDecimal.valueOf(10.00);
    private BigDecimal precoCascolac = BigDecimal.valueOf(11.00);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoRaspagem() {
        return precoRaspagem;
    }

    public void setPrecoRaspagem(BigDecimal precoRaspagem) {
        this.precoRaspagem = precoRaspagem;
    }

    public BigDecimal getPrecoAplicacao() {
        return precoAplicacao;
    }

    public void setPrecoAplicacao(BigDecimal precoAplicacao) {
        this.precoAplicacao = precoAplicacao;
    }

    public BigDecimal getPrecoColocacao() {
        return precoColocacao;
    }

    public void setPrecoColocacao(BigDecimal precoColocacao) {
        this.precoColocacao = precoColocacao;
    }

    public BigDecimal getPrecoBona() {
        return precoBona;
    }

    public void setPrecoBona(BigDecimal precoBona) {
        this.precoBona = precoBona;
    }

    public BigDecimal getPrecoSinteko() {
        return precoSinteko;
    }

    public void setPrecoSinteko(BigDecimal precoSinteko) {
        this.precoSinteko = precoSinteko;
    }

    public BigDecimal getPrecoCascolac() {
        return precoCascolac;
    }

    public void setPrecoCascolac(BigDecimal precoCascolac) {
        this.precoCascolac = precoCascolac;
    }
}
