package br.com.lukas.pisos_api.orcamento;

import br.com.lukas.pisos_api.preco.Preco;
import br.com.lukas.pisos_api.preco.PrecoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrcamentoServiceTest {

    @Mock
    private OrcamentoRepository orcamentoRepository;

    @Mock
    private PrecoRepository precoRepository;

    @InjectMocks
    private OrcamentoService orcamentoService;


    @Test
    void deveCalcularValorOrcamentoCorretamente(){
        OrcamentoRequestDTO orcamentoRequestDTO = new OrcamentoRequestDTO(
                "lukas",
                "111111",
                "rua2",
                Orcamento.Servico.RASPAGEM,
                50.0f,
                Orcamento.Produto.BONA
        );

        Mockito.when(precoRepository.findById(1L)).thenReturn(Optional.of(new Preco()));
        Mockito.when(orcamentoRepository.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));

       OrcamentoResponseDTO resultado = orcamentoService.criarOrcamento(orcamentoRequestDTO);
       Double valorEsperado = 2000.0;
       Double valorResultado = resultado.valor().doubleValue();
       Assertions.assertEquals(valorEsperado, valorResultado);

    }

    @Test
    void deveCalcularOrcamentoSemProduto(){
        OrcamentoRequestDTO dados = new OrcamentoRequestDTO(
                "Maria",
                "11199999",
                "rua A",
                Orcamento.Servico.COLOCACAO,
                10.0f,
                Orcamento.Produto.NENHUM
        );

        Mockito.when(precoRepository.findById(1L)).thenReturn(Optional.of(new Preco()));
        Mockito.when(orcamentoRepository.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));

        OrcamentoResponseDTO resultado = orcamentoService.criarOrcamento(dados);
        BigDecimal valorEsperado = BigDecimal.valueOf(110.0);
        BigDecimal valorResultado = resultado.valor();
        Assertions.assertEquals(valorEsperado, valorResultado);
    }
}