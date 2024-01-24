package br.com.ffrantz;

import br.com.ffrantz.dao.IProdutoDAO;
import br.com.ffrantz.dao.ProdutoDAO;
import br.com.ffrantz.dao.mock.ProdutoDAOMock;
import br.com.ffrantz.service.ProdutoService;
import org.junit.Assert;
import org.junit.Test;

public class ProdutoServiceTest {

    @Test
    public void salvarTest() {
        IProdutoDAO mock = new ProdutoDAOMock();
        ProdutoService service = new ProdutoService(mock);
        String retorno = service.salvar();
        Assert.assertEquals("Salvo",retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroSalvarTest() {
        IProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoService service = new ProdutoService(produtoDAO);
        String retorno = service.salvar();
        Assert.assertEquals("Salvo",retorno);

    }

    @Test
    public void buscarTest() {
        IProdutoDAO mock = new ProdutoDAOMock();
        ProdutoService service = new ProdutoService(mock);
        String retorno = service.buscar();
        Assert.assertEquals("Encontrado",retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroBuscarTest() {
        IProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoService service = new ProdutoService(produtoDAO);
        String retorno = service.buscar();
        Assert.assertEquals("Encontrado",retorno);

    }

    @Test
    public void atualizarTest() {
        IProdutoDAO mock = new ProdutoDAOMock();
        ProdutoService service = new ProdutoService(mock);
        String retorno = service.atualizar();
        Assert.assertEquals("Atualizado",retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroAtualizarTest() {
        IProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoService service = new ProdutoService(produtoDAO);
        String retorno = service.atualizar();
        Assert.assertEquals("Atualizado",retorno);

    }

    @Test
    public void excluirTest() {
        IProdutoDAO mock = new ProdutoDAOMock();
        ProdutoService service = new ProdutoService(mock);
        String retorno = service.excluir();
        Assert.assertEquals("Excluido",retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroExcluirTest() {
        IProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoService service = new ProdutoService(produtoDAO);
        String retorno = service.excluir();
        Assert.assertEquals("Excluido",retorno);

    }
}
