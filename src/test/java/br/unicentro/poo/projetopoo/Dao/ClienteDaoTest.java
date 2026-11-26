package br.unicentro.poo.projetopoo.Dao;

import br.unicentro.poo.projetopoo.Model.Cliente;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDaoTest {

    private final ClienteDao clienteDao = new ClienteDao();

    @Test
    void devePersistirClienteNoBancoDeDados() throws SQLException {
        String cpf = gerarCpfDeTeste();

        try {
            Cliente cliente = criarCliente(cpf, "Cliente Teste Persistencia", "42999990001");

            clienteDao.inserir(cliente);
            List<Cliente> encontrados = clienteDao.listar(cpf);

            assertEquals(1, encontrados.size(),
                    "O cliente inserido deveria ser encontrado no banco de dados");
            assertEquals(cpf, encontrados.get(0).getCpf(),
                    "O CPF recuperado deve ser igual ao CPF que foi cadastrado");
            assertEquals("Cliente Teste Persistencia", encontrados.get(0).getNome(),
                    "O nome do cliente deve permanecer igual após a persistência");
            assertEquals("42999990001", encontrados.get(0).getTelefone(),
                    "O telefone do cliente deve permanecer igual após a persistência");
        } finally {
            clienteDao.excluir(cpf);
        }
    }

    @Test
    void deveAtualizarDadosDoClienteNoBancoDeDados() throws SQLException {
        String cpf = gerarCpfDeTeste();

        try {
            clienteDao.inserir(criarCliente(cpf, "Nome Antigo", "42999990002"));

            Cliente clienteAtualizado = criarCliente(cpf, "Nome Atualizado", "42988887777");
            clienteDao.atualizar(clienteAtualizado);

            List<Cliente> encontrados = clienteDao.listar(cpf);

            assertEquals(1, encontrados.size(),
                    "A atualização não deve criar outro registro de cliente");
            assertEquals("Nome Atualizado", encontrados.get(0).getNome(),
                    "O nome do cliente deveria ter sido atualizado");
            assertEquals("42988887777", encontrados.get(0).getTelefone(),
                    "O telefone do cliente deveria ter sido atualizado");
            assertEquals(cpf, encontrados.get(0).getCpf(),
                    "O CPF deve permanecer o mesmo após a atualização");
        } finally {
            clienteDao.excluir(cpf);
        }
    }

    @Test
    void deveRemoverClienteDoBancoDeDados() throws SQLException {
        String cpf = gerarCpfDeTeste();

        try {
            clienteDao.inserir(criarCliente(cpf, "Cliente Para Remover", "42999990003"));

            // Confirma que o registro existe antes de removê-lo.
            assertEquals(1, clienteDao.listar(cpf).size(),
                    "O cliente deveria existir antes da remoção");

            clienteDao.excluir(cpf);
            List<Cliente> encontrados = clienteDao.listar(cpf);

            assertTrue(encontrados.isEmpty(),
                    "A lista não deveria conter o cliente depois da remoção");
        } finally {
            // Garante a limpeza caso alguma asserção falhe.
            clienteDao.excluir(cpf);
        }
    }

    private Cliente criarCliente(String cpf, String nome, String telefone) {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        return cliente;
    }

    private String gerarCpfDeTeste() {
        // Gera um identificador numérico de 11 dígitos para evitar colisão com dados reais.
        long numero = Math.floorMod(System.nanoTime(), 100_000_000_000L);
        return String.format("%011d", numero);
    }
}
