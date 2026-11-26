package br.unicentro.poo.projetopoo.Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorUtilTest {

    @Test
    void deveRetornarTrueParaCpfValido() {
        assertTrue(ValidadorUtil.isCpfValido("45763488920"), "Deve retornar True para cpfs válidos");
        assertTrue(ValidadorUtil.isCpfValido("286.540.409-98"), "Deve retornar True para cpfs válidos");
    }

    @Test
    void deveRetornarFalseParaCpfInvalido(){
        assertFalse(ValidadorUtil.isCpfValido("28654040993"), "Deve retornar False para cpfs inválidos");
        assertFalse(ValidadorUtil.isCpfValido("12345678910"), "Deve retornar False para cpfs inválidos");
    }

    @Test
    void deveRetornarFalseParaNuloVazio(){
        assertFalse(ValidadorUtil.isCpfValido(""), "Deve retornar False para cpfs vazios");
        assertFalse(ValidadorUtil.isCpfValido(null),"Deve retornar False para cpfs nulos");
    }

    @Test
    void deveRetornarTrueParaTelefoneValido() {
        assertTrue(ValidadorUtil.isTelefoneValido("8238657778"), "Deve retornar True para telefones válidos");
        assertTrue(ValidadorUtil.isTelefoneValido("(32)21309123"), "Deve retornar True para telefones válidos");
    }

    @Test
    void deveRetornarFalseParaTelefoneInvalido() {
        assertFalse(ValidadorUtil.isTelefoneValido("123456789"), "Deve retornar False para telefones inválidos");
        assertFalse(ValidadorUtil.isTelefoneValido("82342017"), "Deve retornar False para telefones inválidos");
    }

    @Test
    void deveRetornarFalseParaTelefoneVazioNulo(){
        assertFalse(ValidadorUtil.isTelefoneValido(""), "Deve retornar False para telefones vazios");
        assertFalse(ValidadorUtil.isTelefoneValido(null), "Deve retornar False para telefones nulos");
    }
}