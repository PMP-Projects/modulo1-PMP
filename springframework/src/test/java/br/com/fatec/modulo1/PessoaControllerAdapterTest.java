package br.com.fatec.modulo1;

import br.com.fatec.modulo1.controller.adapter.PessoaControllerAdapter;
import br.com.fatec.modulo1.controller.dto.request.PessoaCreateRequest;
import br.com.fatec.modulo1.controller.dto.response.PessoaResponse;
import br.com.fatec.modulo1.entity.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PessoaControllerAdapterTest {

    @Test
    void testToEntity() {
        PessoaCreateRequest request = new PessoaCreateRequest("João Silva", LocalDate.of(1990, 1, 1));
        Pessoa pessoa = PessoaControllerAdapter.toEntity(request);

        assertNotNull(pessoa.id(), "ID não deve ser nulo");
        assertEquals("João Silva", pessoa.nome());
        assertEquals(LocalDate.of(1990, 1, 1), pessoa.dataNascimento());
        assertTrue(pessoa.ativo(), "A pessoa deve estar ativa");
    }

    @Test
    void testToResponse() {
        Pessoa pessoa = new Pessoa("123", "Maria Oliveira", LocalDate.of(1995, 5, 5), true);
        PessoaResponse response = PessoaControllerAdapter.toResponse(pessoa);

        assertEquals("123", response.id());
        assertEquals("Maria Oliveira", response.nome());
        assertEquals(LocalDate.of(1995, 5, 5), response.dataNascimento());
        assertTrue(response.ativo());
    }

    @Test
    void testPrivateConstructor() throws Exception {
        var constructor = PessoaControllerAdapter.class.getDeclaredConstructor();
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
