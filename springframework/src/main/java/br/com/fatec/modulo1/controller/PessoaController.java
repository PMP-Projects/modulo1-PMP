package br.com.fatec.modulo1.controller;

import br.com.fatec.modulo1.controller.adapter.PessoaControllerAdapter;
import br.com.fatec.modulo1.controller.dto.request.PessoaCreateRequest;
import br.com.fatec.modulo1.controller.dto.request.PessoaUpdateRequest;
import br.com.fatec.modulo1.controller.dto.response.PessoaResponse;
import br.com.fatec.modulo1.entity.Pessoa;
import br.com.fatec.modulo1.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("modulo1/api/v1/pessoa")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    public PessoaResponse salvar(@RequestBody @Valid PessoaCreateRequest request) {
        Pessoa pessoa = PessoaControllerAdapter.toEntity(request);
        return PessoaControllerAdapter.toResponse(service.salvar(pessoa));
    }

    @PutMapping("/{id}")
    public PessoaResponse atualizar(@PathVariable String id,
                                    @RequestBody @Valid PessoaUpdateRequest request) {
        Pessoa atualizada = service.atualizar(id, request.nome(), request.dataNascimento());
        return PessoaControllerAdapter.toResponse(atualizada);
    }

    @GetMapping("/{id}")
    public PessoaResponse buscar(@PathVariable String id) {
        return PessoaControllerAdapter.toResponse(service.buscarPorId(id));
    }

    @GetMapping
    public Page<PessoaResponse> listar(Pageable pageable) {
        return service.listarAtivos(pageable)
                .map(PessoaControllerAdapter::toResponse);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}
