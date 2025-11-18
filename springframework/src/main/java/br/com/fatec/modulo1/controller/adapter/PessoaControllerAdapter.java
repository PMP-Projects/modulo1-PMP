package br.com.fatec.modulo1.controller.adapter;

import br.com.fatec.modulo1.controller.dto.request.PessoaRequest;
import br.com.fatec.modulo1.controller.dto.response.PessoaResponse;
import br.com.fatec.modulo1.entity.Pessoa;

import java.util.UUID;

public class PessoaControllerAdapter {

    public static Pessoa toEntity(PessoaRequest request) {
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
