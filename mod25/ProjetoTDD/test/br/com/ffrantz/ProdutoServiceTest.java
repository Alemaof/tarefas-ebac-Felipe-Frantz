package br.com.ffrantz;

import br.com.ffrantz.dao.ProdutoDAO;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.exceptions.TipoChaveNaoEncontradaException;
import br.com.ffrantz.service.GenericService;
import br.com.ffrantz.service.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ProdutoServiceTest {

    private GenericService produtoService;

    private Produto produto;

    public ProdutoServiceTest() {
        GenericDAO dao = new ProdutoDAO();
        produtoService = new ProdutoService(dao);
    }

    @Before
    public void init() {
        produto = new Produto();
        produto.setCodigo("A1");
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
    }

    @Test
    public void pesquisar() {
        Produto produtor = (Produto) this.produtoService.buscarPorChave(produto.getCodigo());
        Assert.assertNotNull(produtor);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        Boolean retorno = produtoService.salvar(produto);
        Assert.assertTrue(retorno);
    }

    @Test
    public void excluir() {
        produtoService.excluir(produto.getCodigo());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        produto.setNome("Rodrigo Pires");
        produtoService.alterar(produto);

        Assert.assertEquals("Rodrigo Pires", produto.getNome());
    }
}
