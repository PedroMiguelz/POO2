package br.unicentro.poo.projetopoo.Controller.Entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VeterinarioControllerTest {
    private VeterinarioController veterinarioController;

    @BeforeEach
    void preparar(){
        veterinarioController = new VeterinarioController();
    }

    @Test
    void deveLancarExcecaoAoCadastrarCrmvNuloVeterinario() {
        assertThrows(IllegalArgumentException.class,
                () -> veterinarioController.cadastrarVeterinario(null, "Veterinario", LocalDate.now(), "Cirurgia", "8232555618"), "Deve lançar uma exceção informando que o crmv não pode ser nulo");
    }

    @Test
    void deveLancarExcecaoAoCadastrarCrmvVazioVeterinario() {
        assertThrows(IllegalArgumentException.class,
                () -> veterinarioController.cadastrarVeterinario("", "Veterinario", LocalDate.now(), "Cirurgia", "8232555618"),
                "Deve lançar uma exceção informando que o crmv não pode ser vazio");
    }

    @Test
    void deveLancarExcecaoAoCadastrarNomeVazioVeterinario(){
        assertThrows(IllegalArgumentException.class, () ->
                veterinarioController.cadastrarVeterinario("3567","", LocalDate.now(), "Cirurgia", "8232555618"), "Deve lançar uma exceção informando que o nome não pode ser vazio");
    }

    @Test
    void deveLancarExcecaoAoCadastrarNomeNuloVeterinario(){
        assertThrows(IllegalArgumentException.class, () ->
                veterinarioController.cadastrarVeterinario("3567",null, LocalDate.now(), "Cirurgia", "8232555618"), "Deve lançar uma exceção informando que o nome não pode ser nulo");
    }

}