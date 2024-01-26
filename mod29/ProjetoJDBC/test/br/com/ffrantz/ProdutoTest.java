package br.com.ffrantz;

import br.com.ffrantz.dao.ProdutoDAO;
import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.domain.Produto;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProdutoTest {

    IGenericDAO produtoDAO;

    Produto produto = new Produto();

    @Before
    public void cadastrar() throws SQLException {

        produtoDAO = new ProdutoDAO();

        produto.setNome("Felipe");
        produto.setCodigo(123456789l);
        Integer countCad = produtoDAO.salvar(produto);

        assertTrue(countCad == 1);

    }

    public void excluir(Produto produto1) throws SQLException {
        produtoDAO.excluir(produto1);
    }

    @Test
    public void buscarTeste() throws SQLException {
        Produto produtoBuscado = (Produto) produtoDAO.buscar(123456789l);
        assertNotNull(produtoBuscado);
        assertEquals(produtoBuscado.getNome(),produto.getNome());
        assertEquals(produtoBuscado.getCodigo(),produto.getCodigo());
        excluir(produtoBuscado);
    }

    @Test
    public void atualizarTeste() throws SQLException {
        produto = (Produto) produtoDAO.buscar(produto.getCodigo());
        produto.setNome("Felipe Frantz");
        produto.setCodigo(987654321l);
        Integer countUpd = produtoDAO.atualizar(produto);

        assertTrue(countUpd == 1);

        Produto produtoAtualizado = (Produto) produtoDAO.buscar(produto.getCodigo());
        assertEquals(produto.getCodigo(),produtoAtualizado.getCodigo());
        assertEquals(produto.getNome(),produtoAtualizado.getNome());
        excluir(produtoAtualizado);
    }

    @Test
    public void excluirTest() throws SQLException {
        Integer countExc = produtoDAO.excluir(produto);
        assertTrue(countExc == 1);
    }

    @Test
    public void buscarTodosTest() throws SQLException {
        Produto produto1 = new Produto();
        produto1.setNome("Joao");
        produto1.setCodigo(10101010l);
        produtoDAO.salvar(produto1);
        List<Produto> list = produtoDAO.buscarTodos();

        assertNotNull(list);
        assertEquals(2,list.size());

        list.forEach(c -> {
            try {
                produtoDAO.excluir(c);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        list = produtoDAO.buscarTodos();
        assertEquals(0,list.size());
    }
}
