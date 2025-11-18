package br.com.fatec.modulo1;

import br.com.fatec.modulo1.exception.NotFoundException;
import br.com.fatec.modulo1.integration.client.PokemonIntegrationWithFeign;
import br.com.fatec.modulo1.integration.dto.PokemonApiResponse;
import br.com.fatec.modulo1.integration.dto.PokemonTypeApiResponse;
import br.com.fatec.modulo1.integration.dto.PokemonTypesApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PessoaApplicationTests {

    @Autowired
    private PokemonIntegrationWithFeign integration;

    @Test
    void integrationApiPokemonSuccessTest() {
        PokemonApiResponse apiResponse = integration.getPokemon("pikachu");
        PokemonApiResponse testResponse = new PokemonApiResponse(
                25,
                "pikachu",
                60,
                List.of(new PokemonTypesApiResponse(new PokemonTypeApiResponse("electric"))));
        assertEquals(testResponse, apiResponse);
    }

    @Test
    void integrationApiPokemonNotExistsTest() {
        assertThrows(NotFoundException.class, () -> integration.getPokemon("dragogo"));
    }

}
