package br.com.ffrantz;

import br.com.ffrantz.dao.ClienteDAO;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.exceptions.TipoChaveNaoEncontradaException;
import br.com.ffrantz.service.ClienteService;
import br.com.ffrantz.service.GenericService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteServiceTest {

    private GenericService service;
    private Cliente cliente;

    public ClienteServiceTest() {
        GenericDAO dao = new ClienteDAO();
        service = new ClienteService(dao);
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
        service.salvar(cliente);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setCpf(456456465l);
        Boolean retorno = service.salvar(cliente);
        Assert.assertTrue(retorno);

    }
    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = (Cliente) service.buscarPorChave(cliente.getCpf());

        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void excluirCliente() {
        service.excluir(cliente.getCpf());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Felipe Frantz");
        service.alterar(cliente);

        Assert.assertEquals("Felipe Frantz",cliente.getNome());
    }

}
