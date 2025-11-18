package br.com.fatec.modulo1.repository;

import br.com.fatec.modulo1.entity.Pessoa;
import br.com.fatec.modulo1.exception.BadRequestException;
import br.com.fatec.modulo1.exception.InternalServerException;
import br.com.fatec.modulo1.exception.NotFoundException;
import br.com.fatec.modulo1.repository.adapter.PessoaRepositoryAdapter;
import br.com.fatec.modulo1.repository.client.PessoaRepositoryMongo;
import br.com.fatec.modulo1.repository.orm.PessoaOrm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaRepositoryMongo mongo;

    public PessoaRepositoryImpl(PessoaRepositoryMongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        try {
            PessoaOrm saved = mongo.save(PessoaRepositoryAdapter.toOrm(pessoa));
            return PessoaRepositoryAdapter.toEntity(saved);
        } catch (Exception e) {
            throw new InternalServerException("Erro ao salvar pessoa", e);
        }
    }

    @Override
    public Pessoa findById(String id) {
        PessoaOrm orm = mongo.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        return PessoaRepositoryAdapter.toEntity(orm);
    }

    @Override
    public Page<Pessoa> findAllAtivos(Pageable pageable) {
        return mongo.findByAtivoTrue(pageable)
                .map(PessoaRepositoryAdapter::toEntity);
    }

    @Override
    public void delete(String id) {
        try {
            if (!mongo.existsById(id)) {
                throw new NotFoundException("Pessoa não encontrada");
            }
            PessoaOrm orm = mongo.findById(id).get();
            PessoaOrm desativada = new PessoaOrm(
                    orm.id(),
                    orm.nome(),
                    orm.dataNascimento(),
                    false
            );
            mongo.save(desativada);
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new InternalServerException("Erro ao desativar pessoa", e);
        }
    }
}
