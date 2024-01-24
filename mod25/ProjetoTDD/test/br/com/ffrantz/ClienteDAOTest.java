package br.com.ffrantz;

import br.com.ffrantz.dao.ClienteDAO;
import br.com.ffrantz.dao.IClienteDAO;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.domain.Persistente;
import br.com.ffrantz.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteDAOTest {

    private Cliente cliente;
    private GenericDAO clienteDAO;

    public ClienteDAOTest() {
        clienteDAO = new ClienteDAO();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        cliente = new Cliente();
        cliente.setNome("Felipe");
        cliente.setCpf(123456789l);
        cliente.setEndereco("Endereco");
        cliente.setTelefone(11999999999l);
        cliente.setCidade("Sao Paulo");
        cliente.setEstado("SP");
        clienteDAO.salvar(cliente);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setCpf(45456456l);
        Boolean retorno = clienteDAO.salvar(cliente);
        Assert.assertTrue(retorno);

    }
    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = (Cliente) clienteDAO.buscarPorChave(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void excluirCliente() {
        clienteDAO.excluir(cliente.getCpf());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Felipe Frantz");
        clienteDAO.alterar(cliente);
        Assert.assertEquals("Felipe Frantz",cliente.getNome());
    }
}
