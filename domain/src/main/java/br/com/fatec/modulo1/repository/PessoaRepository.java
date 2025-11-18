package br.com.fatec.modulo1.repository;

import br.com.fatec.modulo1.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepository {
    Pessoa save(Pessoa pessoa);

    Pessoa findById(String id);

    Page<Pessoa> findAllAtivos(Pageable pageable);

    void delete(String id);
}
