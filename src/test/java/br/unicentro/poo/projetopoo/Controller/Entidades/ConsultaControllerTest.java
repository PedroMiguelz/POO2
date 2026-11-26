package br.unicentro.poo.projetopoo.Controller.Entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ConsultaControllerTest {
    private ConsultaController consultaController;

    @BeforeEach
    void preparar(){
        consultaController = new ConsultaController();
    }

    @Test
    void deveLancarExcecaoQuandoDataForNulaConsulta(){
        assertThrows(IllegalArgumentException.class, () ->
                consultaController.cadastrarConsulta(null, BigDecimal.valueOf(100), 1, "12345"), "Deve lançar uma uma exceção informando para selecionar uma data válida");
    }

    @Test
    void deveLancarExcecaoQuandoValorForNuloConsulta(){
        assertThrows(IllegalArgumentException.class, () ->
                consultaController.cadastrarConsulta(LocalDate.now(), null, 1, "12345"), "Deve lançar uma uma exceção informando para informar um valor válido");
    }

    @Test
    void deveLancarExcecaoAoCadastrarIdAnimalZeroConsulta(){
        assertThrows(IllegalArgumentException.class, () ->
                consultaController.cadastrarConsulta(LocalDate.now(), BigDecimal.valueOf(100),0, "12345"), "Deve lançar uma exceção informando para selecionar uma animal válido");
    }

    @Test
    void deveLancarExcecaoAoCadastrarIdAnimalNegativoConsulta(){
        assertThrows(IllegalArgumentException.class, () ->
                consultaController.cadastrarConsulta(LocalDate.now(), BigDecimal.valueOf(100),-1, "12345"), "Deve lançar uma exceção informando para selecionar uma animal válido");
    }

    @Test
    void deveLancarExcecaoQuandoDataInicialForNula() {
        assertThrows(IllegalArgumentException.class, () ->
                consultaController.calcularTotalFaturado(null, LocalDate.now()), "Deve lançar uma exceção informando que a data inicial e a data final são obrigatórias");
    }

    @Test
    void deveLancarExcecaoQuandoDataFinalForNula() {
        assertThrows(IllegalArgumentException.class, () ->
                consultaController.calcularTotalFaturado(LocalDate.now(), null),"Deve lançar uma exceção informando que a data inicial e a data final são obrigatórias");
    }

    @Test
    void deveLancarExcecaoQuandoDataInicialForPosteriorADataFinal() {

        LocalDate inicio = LocalDate.of(2026, 11, 25);
        LocalDate fim = LocalDate.of(2026, 11, 20);

        assertThrows(IllegalArgumentException.class, () ->
                consultaController.calcularTotalFaturado(inicio, fim),"Deve lançar uma exceção informando que a data inicial não pode ser posterior à data final");
    }
}