package br.com.fatec.modulo1.entity;

import java.time.LocalDate;

public record Pessoa(
        String id,
        String nome,
        LocalDate dataNascimento,
        Boolean ativo
) {}
