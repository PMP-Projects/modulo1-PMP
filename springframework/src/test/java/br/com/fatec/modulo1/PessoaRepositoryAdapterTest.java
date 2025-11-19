package br.com.fatec.modulo1;

import br.com.fatec.modulo1.entity.Pessoa;
import br.com.fatec.modulo1.repository.adapter.PessoaRepositoryAdapter;
import br.com.fatec.modulo1.repository.orm.PessoaOrm;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PessoaRepositoryAdapterTest {

    @Test
    void testToEntity() {
        PessoaOrm orm = new PessoaOrm("1", "João Silva", LocalDate.of(1990, 1, 1), true);
        Pessoa entity = PessoaRepositoryAdapter.toEntity(orm);

        assertNotNull(entity);
        assertEquals("1", entity.id());
        assertEquals("João Silva", entity.nome());
        assertEquals(LocalDate.of(1990, 1, 1), entity.dataNascimento());
        assertTrue(entity.ativo());
    }

    @Test
    void testToOrm() {
        Pessoa entity = new Pessoa("2", "Maria Oliveira", LocalDate.of(1995, 5, 5), false);
        PessoaOrm orm = PessoaRepositoryAdapter.toOrm(entity);

        assertNotNull(orm);
        assertEquals("2", orm.id());
        assertEquals("Maria Oliveira", orm.nome());
        assertEquals(LocalDate.of(1995, 5, 5), orm.dataNascimento());
        assertFalse(orm.ativo());
    }

    @Test
    void testPrivateConstructor() throws Exception {
        var constructor = PessoaRepositoryAdapter.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        try {
            constructor.newInstance();
            fail("Esperava UnsupportedOperationException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            // Verifica se a causa real é UnsupportedOperationException
            assertTrue(e.getCause() instanceof UnsupportedOperationException);
            assertEquals("Classe utilitária não pode ser instanciada", e.getCause().getMessage());
        }
    }
}
