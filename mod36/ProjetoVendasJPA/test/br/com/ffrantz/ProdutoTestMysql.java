package br.com.ffrantz;

import br.com.ffrantz.dao.IProdutoDAO;
import br.com.ffrantz.dao.ProdutoDAOMysql;
import br.com.ffrantz.dao.ProdutoDAOPostgre;
import br.com.ffrantz.entity.Produto;
import br.com.ffrantz.service.IProdutoService;
import br.com.ffrantz.service.ProdutoService;
import jakarta.persistence.EntityExistsException;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProdutoTestMysql {

    private IProdutoService produtoService;

    private IProdutoDAO produtoDAO;

    private Produto produto;

    public ProdutoTestMysql() {
        this.produtoDAO = new ProdutoDAOMysql();
        this.produtoService = new ProdutoService(produtoDAO);
        produto = cadastrarProduto(123l);
    }
    @Test
    public void cadastrarTest() {

        produto = produtoService.cadastrar(produto);

        assertNotNull(produto);
        assertNotNull(produto.getId());
    }

    @Test
    public void buscarTest() {
        produto = produtoService.cadastrar(produto);

        Produto produtoBuscado = produtoService.consultar(produto.getId());

        assertNotNull(produtoBuscado);
        assertEquals(produtoBuscado.getNome(),produto.getNome());
    }

    @Test
    public void atualizarTest() {

        produto = produtoService.cadastrar(produto);
        produto.setNome("Gabriel");

        Produto produtoAtualiado = produtoService.alterar(produto);

        assertNotNull(produtoAtualiado);
        assertEquals(produto.getNome(), produtoAtualiado.getNome());
    }

    @Test
    public void excluirTest() {
        Produto produtoCadastrado = produtoService.cadastrar(produto);
        produtoService.excluir(produtoCadastrado);

        produtoCadastrado = produtoService.consultar(produtoCadastrado.getId());

        assertNull(produtoCadastrado);
    }

    @Test
    public void buscarTodosTest() {
        produtoService.cadastrar(produto);
        produtoService.cadastrar(cadastrarProduto(987654321l));
        produtoService.cadastrar(cadastrarProduto(9876541l));

        Collection<Produto> produtos = produtoService.buscarTodos();

        assertEquals(produtos.size(),3);
    }

    @Test(expected = EntityExistsException.class)
    public void cadastrarMesmoCPF() {
        produto = produtoService.cadastrar(produto);
        Produto produto2 = produtoService.cadastrar(produto);

        assertEquals(produto,produto2);
    }

    @After
    public void excluirTodos() {
        Collection<Produto> produtos = produtoService.buscarTodos();
        produtos.forEach(produto ->
                produtoService.excluir(produto));
    }

    private Produto cadastrarProduto(Long codigo) {
        Produto produto = new Produto();
        produto.setNome("Televisao");
        produto.setCodigo(codigo);
        produto.setDescricao("Descricao");
        produto.setQuantidade(3);
        produto.setValor(BigDecimal.TEN);
        return produto;
    }
}
