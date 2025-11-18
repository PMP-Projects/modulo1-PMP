package br.com.fatec.modulo1.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("pessoa")
public record PessoaOrm(
        @Id
        String id,
        String nome,
        LocalDate dataNascimento,
        Boolean ativo
) {}
