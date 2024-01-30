package br.com.ffrantz;

import br.com.ffrantz.dao.IProdutoDAO;
import br.com.ffrantz.dao.ProdutoDAO;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProdutoTest {

    IProdutoDAO produtoDAO;

    Produto produto = new Produto();

    @Before
    public void cadastrar() throws DAOException {

        produtoDAO = new ProdutoDAO();

        produto.setNome("Televisao");
        produto.setCodigo(123456789l);
        produto.setDescricao("Descricao");
        produto.setValor(BigDecimal.TEN);
        produto.setQuantidade(4);
        Integer countCad = produtoDAO.salvar(produto);

        assertTrue(countCad == 1);

    }

    public void excluir(Produto produto1) throws DAOException {
        produtoDAO.excluir(produto1);
    }

    @Test
    public void buscarTeste() throws DAOException, MaisDeUmRegistroException {
        Produto produtoBuscado = (Produto) produtoDAO.buscar(123456789l);
        assertNotNull(produtoBuscado);
        assertEquals(produtoBuscado.getNome(),produto.getNome());
        assertEquals(produtoBuscado.getCodigo(),produto.getCodigo());
        assertEquals(produtoBuscado.getDescricao(),produto.getDescricao());
        assertEquals(produtoBuscado.getValor().longValue(),produto.getValor().longValue());
        assertEquals(produtoBuscado.getQuantidade(),produto.getQuantidade());
        excluir(produtoBuscado);
    }

    @Test
    public void atualizarTeste() throws DAOException, MaisDeUmRegistroException {
        produto = (Produto) produtoDAO.buscar(produto.getCodigo());
        produto.setNome("TV Samsung");
        produto.setCodigo(987654321l);
        Integer countUpd = produtoDAO.atualizar(produto);

        assertTrue(countUpd == 1);

        Produto produtoAtualizado = (Produto) produtoDAO.buscar(produto.getCodigo());
        assertEquals(produto.getCodigo(),produtoAtualizado.getCodigo());
        assertEquals(produto.getNome(),produtoAtualizado.getNome());
        excluir(produtoAtualizado);
    }

    @Test
    public void atualizarEstoque() throws DAOException, MaisDeUmRegistroException {
        produtoDAO = new ProdutoDAO();
        Integer countEstoque = produtoDAO.addEstoque(produto.getId(),3);

        assertTrue(countEstoque == 1);

        Produto produtoAtualizado = (Produto) produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoAtualizado.getQuantidade() == 7);
        excluir(produto);
    }

    @Test
    public void excluirTest() throws DAOException {
        Integer countExc = produtoDAO.excluir(produto);
        assertTrue(countExc == 1);
    }

    @Test
    public void buscarTodosTest() throws DAOException {
        Produto produto1 = new Produto();
        produto1.setNome("PC");
        produto1.setCodigo(10101010l);
        produto1.setDescricao("Descricao2");
        produto1.setValor(BigDecimal.TWO);
        produto1.setQuantidade(2);
        produtoDAO.salvar(produto1);
        List<Produto> list = produtoDAO.buscarTodos();

        assertNotNull(list);
        assertEquals(2,list.size());

        list.forEach(c -> {
            try {
                produtoDAO.excluir(c);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        });
        list = produtoDAO.buscarTodos();
        assertEquals(0,list.size());
    }
}
