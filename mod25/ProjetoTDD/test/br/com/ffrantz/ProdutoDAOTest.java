package br.com.ffrantz;

import br.com.ffrantz.dao.ProdutoDAO;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ProdutoDAOTest {

    private GenericDAO produtoDao;

    private Produto produto;

    public ProdutoDAOTest() {
        produtoDao = new ProdutoDAO();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        produto = new Produto();
        produto.setCodigo("A1");
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produtoDao.salvar(produto);
    }

    @Test
    public void pesquisar() {
        Produto produto = (Produto) this.produtoDao.buscarPorChave(this.produto.getCodigo());
        Assert.assertNotNull(produto);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        produto.setCodigo("A2");
        Boolean retorno = produtoDao.salvar(produto);
        Assert.assertTrue(retorno);
    }

    @Test
    public void excluir() {
        produtoDao.excluir(produto.getCodigo());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        produto.setNome("Rodrigo Pires");
        produtoDao.alterar(produto);

        Assert.assertEquals("Rodrigo Pires", produto.getNome());
    }
}
