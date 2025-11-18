package br.com.fatec.modulo1.controller.dto.response;

import java.time.LocalDate;

public record PessoaResponse(
        String id,
        String nome,
        LocalDate dataNascimento,
        Boolean ativo
) {}
