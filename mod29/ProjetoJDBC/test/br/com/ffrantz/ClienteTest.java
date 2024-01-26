package br.com.ffrantz;

import br.com.ffrantz.dao.ClienteDAO;
import br.com.ffrantz.dao.IClienteDAO;
import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.domain.Cliente;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ClienteTest {

    IGenericDAO clienteDAO;

    Cliente cliente = new Cliente();

    @Before
    public void cadastrar() throws SQLException {

        clienteDAO = new ClienteDAO();

        cliente.setNome("Felipe");
        cliente.setCpf(123456789l);
        Integer countCad = clienteDAO.salvar(cliente);

        assertTrue(countCad == 1);

    }

    public void excluir(Cliente cliente1) throws SQLException {
        clienteDAO.excluir(cliente1);
    }

    @Test
    public void buscarTeste() throws SQLException {
        Cliente clienteBuscado = (Cliente) clienteDAO.buscar(123456789l);
        assertNotNull(clienteBuscado);
        assertEquals(clienteBuscado.getNome(),cliente.getNome());
        assertEquals(clienteBuscado.getCpf(),cliente.getCpf());
        excluir(clienteBuscado);
    }

    @Test
    public void atualizarTeste() throws SQLException {
        cliente = (Cliente) clienteDAO.buscar(cliente.getCpf());
        cliente.setNome("Felipe Frantz");
        cliente.setCpf(987654321l);
        Integer countUpd = clienteDAO.atualizar(cliente);

        assertTrue(countUpd == 1);

        Cliente clienteAtualizado = (Cliente) clienteDAO.buscar(cliente.getCpf());
        assertEquals(cliente.getCpf(),clienteAtualizado.getCpf());
        assertEquals(cliente.getNome(),clienteAtualizado.getNome());
        excluir(clienteAtualizado);
    }

    @Test
    public void excluirTest() throws SQLException {
        Integer countExc = clienteDAO.excluir(cliente);
        assertTrue(countExc == 1);
    }

    @Test
    public void buscarTodosTest() throws SQLException {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Joao");
        cliente1.setCpf(10101010l);
        clienteDAO.salvar(cliente1);
        List<Cliente> list = clienteDAO.buscarTodos();

        assertNotNull(list);
        assertEquals(2,list.size());

        list.forEach(c -> {
            try {
                clienteDAO.excluir(c);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        list = clienteDAO.buscarTodos();
        assertEquals(0,list.size());
    }
}
