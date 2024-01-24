package br.com.ffrantz;

import br.com.ffrantz.dao.ClienteDAO;
import br.com.ffrantz.dao.IClienteDAO;
import br.com.ffrantz.dao.mock.ClienteDAOMock;
import br.com.ffrantz.service.ClienteService;
import org.junit.Assert;
import org.junit.Test;

public class ClienteServiceTest {

    @Test
    public void salvarTest() {
        IClienteDAO mock = new ClienteDAOMock();
        ClienteService service = new ClienteService(mock);
        String retorno = service.salvar();
        Assert.assertEquals("Salvo",retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroSalvarTest() {
        IClienteDAO clienteDAO = new ClienteDAO();
        ClienteService service = new ClienteService(clienteDAO);
        String retorno = service.salvar();
        Assert.assertEquals("Salvo",retorno);

    }

    @Test
    public void buscarTest() {
        IClienteDAO mock = new ClienteDAOMock();
        ClienteService service = new ClienteService(mock);
        String retorno = service.buscar();
        Assert.assertEquals("Encontrado",retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroBuscarTest() {
        IClienteDAO clienteDAO = new ClienteDAO();
        ClienteService service = new ClienteService(clienteDAO);
        String retorno = service.buscar();
        Assert.assertEquals("Encontrado",retorno);

    }

    @Test
    public void atualizarTest() {
        IClienteDAO mock = new ClienteDAOMock();
        ClienteService service = new ClienteService(mock);
        String retorno = service.atualizar();
        Assert.assertEquals("Atualizado",retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroAtualizarTest() {
        IClienteDAO clienteDAO = new ClienteDAO();
        ClienteService service = new ClienteService(clienteDAO);
        String retorno = service.atualizar();
        Assert.assertEquals("Atualizado",retorno);

    }

    @Test
    public void excluirTest() {
        IClienteDAO mock = new ClienteDAOMock();
        ClienteService service = new ClienteService(mock);
        String retorno = service.excluir();
        Assert.assertEquals("Excluido",retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroExcluirTest() {
        IClienteDAO clienteDAO = new ClienteDAO();
        ClienteService service = new ClienteService(clienteDAO);
        String retorno = service.excluir();
        Assert.assertEquals("Excluido",retorno);

    }
}
