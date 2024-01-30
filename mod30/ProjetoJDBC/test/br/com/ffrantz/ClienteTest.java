package br.com.ffrantz;

import br.com.ffrantz.dao.ClienteDAO;
import br.com.ffrantz.dao.IClienteDAO;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClienteTest {

    IClienteDAO clienteDAO;

    Cliente cliente = new Cliente();

    @Before
    public void cadastrar() throws DAOException {

        clienteDAO = new ClienteDAO();

        cliente.setNome("Felipe");
        cliente.setCpf(123456789l);
        cliente.setIdade(38);
        cliente.setTelefone(119999999l);
        cliente.setEndereco("Endereco");
        cliente.setCidade("Sao Paulo");
        cliente.setEstado("SP");
        Integer countCad = clienteDAO.salvar(cliente);

        assertTrue(countCad == 1);

    }

    public void excluir(Cliente cliente) throws DAOException {
        clienteDAO.excluir(cliente);
    }

    @Test
    public void buscarTeste() throws DAOException, MaisDeUmRegistroException {
        Cliente clienteBuscado = clienteDAO.buscar(123456789l);
        assertNotNull(clienteBuscado);
        assertEquals(clienteBuscado.getNome(),cliente.getNome());
        assertEquals(clienteBuscado.getCpf(),cliente.getCpf());
        assertEquals(clienteBuscado.getIdade(),cliente.getIdade());
        assertEquals(clienteBuscado.getTelefone(),cliente.getTelefone());
        assertEquals(clienteBuscado.getEndereco(),cliente.getEndereco());
        assertEquals(clienteBuscado.getCidade(),cliente.getCidade());
        assertEquals(clienteBuscado.getEstado(),cliente.getEstado());
        excluir(clienteBuscado);
    }

    @Test
    public void atualizarTeste() throws DAOException, MaisDeUmRegistroException {
        cliente = clienteDAO.buscar(cliente.getCpf());
        cliente.setNome("Felipe Frantz");
        cliente.setCpf(987654321l);
        Integer countUpd = clienteDAO.atualizar(cliente);

        assertTrue(countUpd == 1);

        Cliente clienteAtualizado = clienteDAO.buscar(cliente.getCpf());
        assertEquals(cliente.getCpf(),clienteAtualizado.getCpf());
        assertEquals(cliente.getNome(),clienteAtualizado.getNome());
        excluir(clienteAtualizado);
    }

    @Test
    public void excluirTest() throws DAOException {
        Integer countExc = clienteDAO.excluir(cliente);
        assertTrue(countExc == 1);
    }

    @Test
    public void buscarTodosTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setNome("Joao");
        cliente.setCpf(10101010l);
        cliente.setIdade(33);
        cliente.setTelefone(11891915l);
        cliente.setEndereco("Endereco2");
        cliente.setCidade("Sao Paulo");
        cliente.setEstado("SP");
        clienteDAO.salvar(cliente);
        List<Cliente> list = clienteDAO.buscarTodos();

        assertNotNull(list);
        assertEquals(2,list.size());

        list.forEach(c -> {
            try {
                clienteDAO.excluir(c);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        });
        list = clienteDAO.buscarTodos();
        assertEquals(0,list.size());
    }
}
