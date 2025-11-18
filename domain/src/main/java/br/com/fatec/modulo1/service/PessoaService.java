package br.com.fatec.modulo1.service;

import br.com.fatec.modulo1.entity.Pessoa;
import br.com.fatec.modulo1.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private static final Logger log = LoggerFactory.getLogger(PessoaService.class);

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa salvar(Pessoa pessoa) {
        log.info("Salvando pessoa: nome={}, dataNascimento={}", pessoa.nome(), pessoa.dataNascimento());
        return repository.save(pessoa);
    }

    public Pessoa buscarPorId(String id) {
        log.info("Buscando pessoa por ID: {}", id);
        return repository.findById(id);
    }

    public Page<Pessoa> listarAtivos(Pageable pageable) {
        log.info("Listando pessoas ativas: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAllAtivos(pageable);
    }

    public Pessoa atualizar(String id, String novoNome, java.time.LocalDate novaDataNascimento) {
        log.info("Atualizando pessoa ID={} nome='{}'", id, novoNome);
        Pessoa existente = repository.findById(id);
        Pessoa atualizada = new Pessoa(
                existente.id(),
                novoNome,
                novaDataNascimento,
                existente.ativo()
        );
        return repository.save(atualizada);
    }

    public void deletar(String id) {
        log.info("Desativando pessoa ID: {}", id);
        Pessoa pessoa = repository.findById(id);
        Pessoa pessoaDesativada = new Pessoa(
                pessoa.id(),
                pessoa.nome(),
                pessoa.dataNascimento(),
                false
        );
        repository.save(pessoaDesativada);
    }

}
