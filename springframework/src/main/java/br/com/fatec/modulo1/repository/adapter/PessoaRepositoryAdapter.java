package br.com.fatec.modulo1.repository.adapter;

import br.com.fatec.modulo1.entity.Pessoa;
import br.com.fatec.modulo1.repository.orm.PessoaOrm;

public class PessoaRepositoryAdapter {

    public static Pessoa toEntity(PessoaOrm orm) {
        return new Pessoa(
                orm.id(),
                orm.nome(),
                orm.dataNascimento(),
                orm.ativo()
        );
    }

    public static PessoaOrm toOrm(Pessoa entity) {
        return new PessoaOrm(
                entity.id(),
                entity.nome(),
                entity.dataNascimento(),
                entity.ativo()
        );
    }
}
