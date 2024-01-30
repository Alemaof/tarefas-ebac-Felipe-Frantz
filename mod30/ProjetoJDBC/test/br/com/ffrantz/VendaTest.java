package br.com.ffrantz;

import br.com.ffrantz.connection.ConnectionFactory;
import br.com.ffrantz.dao.*;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.domain.Venda;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collection;

import static org.junit.Assert.*;

public class VendaTest {

    private IVendaDAO vendaDAO;

    private IClienteDAO clienteDAO;

    private IProdutoDAO produtoDAO;

    private Cliente cliente;

    private Produto produto;

    public VendaTest() {
        vendaDAO = new VendaDAO();
        clienteDAO = new ClienteDAO();
        produtoDAO = new ProdutoDAO();
    }

    @Before
    public void init() throws DAOException {
        this.cliente = cadastrarCliente();
        this.produto = cadastrarProduto(1l, BigDecimal.TEN);
    }

    @After
    public void end() throws SQLException, DAOException {
        excluirVendas();
        excluirProdutos();
        clienteDAO.excluir(this.cliente);
    }


    private void excluirProdutos() throws DAOException {
        Collection<Produto> list = this.produtoDAO.buscarTodos();
        for (Produto prod : list) {
            this.produtoDAO.excluir(prod);
        }
    }

    @Test
    public void pesquisar() throws DAOException, MaisDeUmRegistroException, SQLException {
        Venda venda = criarVenda(1l);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        Venda vendaConsultada = vendaDAO.buscar(venda.getCodigo());
        assertNotNull(vendaConsultada);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }

    @Test
    public void salvar() throws DAOException, MaisDeUmRegistroException, SQLException {
        Venda venda = criarVenda(2l);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);

        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));

        Venda vendaConsultada = vendaDAO.buscar(venda.getCodigo());

        assertTrue(vendaConsultada.getId() != null);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }


    @Test
    public void cancelarVenda() throws DAOException, MaisDeUmRegistroException, SQLException {
        Long codigoVenda = 3l;
        Venda venda = criarVenda(codigoVenda);
        Produto produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 1);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        vendaDAO.cancelarVenda(venda);
        produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);

        Venda vendaConsultada = vendaDAO.buscar(codigoVenda);
        assertEquals(codigoVenda, vendaConsultada.getCodigo());
        assertEquals(Venda.Status.CANCELADA, vendaConsultada.getStatus());
    }

    @Test
    public void adicionarMaisProdutosDoMesmo() throws DAOException, MaisDeUmRegistroException, SQLException {
        Long codigoVenda = 4l;
        Venda venda = criarVenda(codigoVenda);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 1);

        Venda vendaConsultada = vendaDAO.buscar(codigoVenda);
        vendaDAO.adicionarProduto(produto, 1, vendaConsultada);

        produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 0);

        assertTrue(vendaDAO.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(30).setScale(0, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void adicionarMaisProdutosDiferentes() throws DAOException, MaisDeUmRegistroException, SQLException {
        Long codigoVenda = 5l;
        Venda venda = criarVenda(codigoVenda);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 1);

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);

        Venda vendaConsultada = vendaDAO.buscar(codigoVenda);
        vendaDAO.adicionarProduto(prod, 1, vendaConsultada);

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 2);

        assertTrue(vendaDAO.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(0, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test(expected = DAOException.class)
    public void salvarVendaMesmoCodigoExistente() throws DAOException, MaisDeUmRegistroException, SQLException {
        Venda venda = criarVenda(6l);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);

        Integer retorno1 = vendaDAO.salvar(venda);
        assertFalse(retorno1 == 1);
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void removerProduto() throws DAOException, MaisDeUmRegistroException, SQLException {
        Long codigoVenda = 7l;
        Venda venda = criarVenda(codigoVenda);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 1);

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);

        Venda vendaConsultada = vendaDAO.buscar(codigoVenda);
        vendaDAO.adicionarProduto(prod, 1, vendaConsultada);

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 2);

        assertTrue(vendaDAO.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(0, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));


        vendaDAO.removerProduto(prod, 1, vendaConsultada);
        assertTrue(vendaDAO.getQuantidadeTotalProdutos() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(0, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void removerApenasUmProduto() throws DAOException, MaisDeUmRegistroException, SQLException {
        Long codigoVenda = 8l;
        Venda venda = criarVenda(codigoVenda);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 1);

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);

        Venda vendaConsultada = vendaDAO.buscar(codigoVenda);
         vendaDAO.adicionarProduto(prod, 1, vendaConsultada);

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 2);

        assertTrue(vendaDAO.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(0, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));


        vendaDAO.removerProduto(prod, 1, vendaConsultada);

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);

        assertTrue(vendaDAO.getQuantidadeTotalProdutos() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(0, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void removerTodosProdutos() throws DAOException, MaisDeUmRegistroException, SQLException {
        Long codigoVenda = 9l;
        Venda venda = criarVenda(codigoVenda);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 1);

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);

        Venda vendaConsultada = vendaDAO.buscar(codigoVenda);
        vendaDAO.adicionarProduto(prod, 1, vendaConsultada);
        assertTrue(vendaDAO.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(0, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 2);


        vendaDAO.removerTodosProdutos(vendaConsultada);
        assertTrue(vendaDAO.getQuantidadeTotalProdutos() == 0);
        assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(0)));
        assertTrue(vendaConsultada.getStatus().equals(Venda.Status.INICIADA));

        produtoBuscado = produtoDAO.buscar(produto.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);

        produtoBuscado = produtoDAO.buscar(prod.getCodigo());
        assertTrue(produtoBuscado.getQuantidade() == 3);
    }

    @Test
    public void finalizarVenda() throws DAOException, MaisDeUmRegistroException, SQLException {
        Long codigoVenda = 10l;
        Venda venda = criarVenda(codigoVenda);
        Integer retorno = vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        vendaDAO.finalizarVenda(venda);

        Venda vendaConsultada = vendaDAO.buscar(codigoVenda);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(Venda.Status.CONCLUIDA, vendaConsultada.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tentarAdicionarProdutosVendaFinalizada() throws DAOException, MaisDeUmRegistroException, SQLException {
        Long codigoVenda = 11l;
        Venda venda = criarVenda(codigoVenda);
        Integer retorno =vendaDAO.salvar(venda);
        assertTrue(retorno == 1);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        vendaDAO.finalizarVenda(venda);
        Venda vendaConsultada = vendaDAO.buscar(codigoVenda);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(Venda.Status.CONCLUIDA, vendaConsultada.getStatus());

        vendaDAO.adicionarProduto(this.produto, 1, vendaConsultada);

    }

    private Produto cadastrarProduto(Long codigo, BigDecimal valor) throws DAOException {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(valor);
        produto.setQuantidade(3);
        produtoDAO.salvar(produto);
        return produto;
    }

    private Cliente cadastrarCliente() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(12312312312L);
        cliente.setNome("Felipe");
        cliente.setIdade(38);
        cliente.setCidade("São Paulo");
        cliente.setEndereco("End");
        cliente.setEstado("SP");
        cliente.setTelefone(1199999999L);
        clienteDAO.salvar(cliente);
        return cliente;
    }

    private Venda criarVenda(Long codigo) throws DAOException, MaisDeUmRegistroException, SQLException {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setDataDaVenda(Instant.now());
        venda.setCliente(this.cliente);
        venda.setStatus(Venda.Status.INICIADA);
        vendaDAO.adicionarProduto(this.produto, 2, venda);
        return venda;
    }

    private void excluirVendas() throws SQLException {
        String sqlProd = "DELETE FROM TB_PRODUTOSVENDIDOS";
        executeDelete(sqlProd);

        String sqlV = "DELETE FROM TB_VENDA";
        executeDelete(sqlV);
    }

    private void executeDelete(String sql) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement(sql);
            stm.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            closeConnection(connection, stm, rs);
        }
    }

    protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !stm.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        try {
            return ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw e;
        }
    }
}
