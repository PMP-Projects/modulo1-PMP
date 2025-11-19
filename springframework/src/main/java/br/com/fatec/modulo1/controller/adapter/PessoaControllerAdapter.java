package br.com.fatec.modulo1.controller.adapter;

import br.com.fatec.modulo1.controller.dto.request.PessoaCreateRequest;
import br.com.fatec.modulo1.controller.dto.response.PessoaResponse;
import br.com.fatec.modulo1.entity.Pessoa;

import java.util.UUID;

public final class PessoaControllerAdapter {

    private PessoaControllerAdapter() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instanciada");
    }

    public static Pessoa toEntity(PessoaCreateRequest request) {
        return new Pessoa(
                UUID.randomUUID().toString(),
                request.nome(),
                request.dataNascimento(),
                true
        );
    }


    public static PessoaResponse toResponse(Pessoa pessoa) {
        return new PessoaResponse(
                pessoa.id(),
                pessoa.nome(),
                pessoa.dataNascimento(),
                pessoa.ativo()
        );
    }
}
