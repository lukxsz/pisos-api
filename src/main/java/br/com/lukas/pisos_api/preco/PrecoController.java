package br.com.lukas.pisos_api.preco;


import br.com.lukas.pisos_api.orcamento.OrcamentoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/precos")
public class PrecoController {
    private final OrcamentoService orcamentoService;

    public PrecoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    @GetMapping
    public Preco buscaTabelaPreco(

    ){
        return orcamentoService.obterTabelaPreco();
    }

    @PutMapping
    public Preco atualizaTabelaPreco(
            @RequestBody Preco novosPrecos
    ){
        return orcamentoService.atualizarTabelaPreco(novosPrecos);
    }
}
