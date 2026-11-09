package br.com.lukas.pisos_api.orcamento;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {
    private final OrcamentoService orcamentoService;

    public OrcamentoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    @GetMapping
    public List<Orcamento> listar(){
       return orcamentoService.listarOrcamentos();
    }

    @PostMapping
    public Orcamento criar(@RequestBody Orcamento orcamento){
        return orcamentoService.criarOrcamento(orcamento);
    }
}
