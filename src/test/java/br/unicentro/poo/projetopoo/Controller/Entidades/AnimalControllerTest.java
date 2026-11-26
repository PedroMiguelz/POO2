package br.unicentro.poo.projetopoo.Controller.Entidades;

import br.unicentro.poo.projetopoo.Dao.AnimalDao;
import br.unicentro.poo.projetopoo.Model.Animal;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalControllerTest {

    @Test
    void deveLancarExcecaoAoCadastrarAnimalSemNomeOuCpf() {
        AnimalController controller = new AnimalController();

        assertThrows(IllegalArgumentException.class,
                () -> controller.cadastrarAnimal("   ", "Cachorro", "SRD", "F", "12345678900"),
                "Esperava IllegalArgumentException quando o nome do animal está em branco");
    }

    @Test
    void deveLancarExcecaoParaIdInvalidoNaExclusao() {
        AnimalController controller = new AnimalController();
        int idInvalido = 0;

        assertThrows(IllegalArgumentException.class,
                () -> controller.excluirAnimal(idInvalido),
                "Esperava IllegalArgumentException ao tentar excluir animal com ID <= 0 (informado: " + idInvalido + ")");
    }

    @Test
    void deveConverterTermoNuloParaVazioAoBuscarAnimais() throws SQLException {
        Animal esperado = new Animal();
        esperado.setNome("Thor");

        AnimalDao daoStub = new AnimalDao() {
            @Override
            public List<Animal> listar(String termo) throws SQLException {
                assertEquals("", termo, "O termo nulo deveria ser convertido para string vazia antes de chamar o DAO");
                return List.of(esperado);
            }
        };
        AnimalController controller = new AnimalController();

        List<Animal> resultado = controller.buscarAnimais(null);

        assertAll("Verificação da busca com termo nulo",
                () -> assertNotNull(resultado, "A lista retornada não deveria ser nula"),
                () -> assertEquals(1, resultado.size(), "Deveria retornar exatamente 1 animal na lista"),
                () -> assertEquals("Thor", resultado.get(0).getNome(), "O nome do animal retornado deve ser 'Thor'")
        );
    }
}