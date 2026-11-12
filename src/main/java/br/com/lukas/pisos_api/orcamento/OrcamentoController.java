package br.com.lukas.pisos_api.orcamento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
    public Orcamento criar(@RequestBody @Valid OrcamentoRequestDTO dados){
        return orcamentoService.criarOrcamento(dados);
    }

    @PatchMapping("/{id}/status")
    public Orcamento atualizarStatus(
            @PathVariable Long id,
            @RequestParam Orcamento.StatusOrcamento novoStatus
    ){
        return orcamentoService.atualizarStatus(id, novoStatus);
    }
}
