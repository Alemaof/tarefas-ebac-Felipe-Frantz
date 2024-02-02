package br.com.ffrantz;

import br.com.ffrantz.dao.*;
import br.com.ffrantz.entity.Cliente;
import br.com.ffrantz.entity.Produto;
import br.com.ffrantz.entity.Venda;
import br.com.ffrantz.service.IVendaService;
import br.com.ffrantz.service.VendaServiceMysql;
import br.com.ffrantz.service.VendaServicePostgre;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class VendaTestMysql {

    private IVendaDAO vendaDAO;

    private IClienteDAO clienteDAO;

    private IProdutoDAO produtoDAO;

    IVendaService vendaService;

    private Cliente cliente;

    private Produto produto;

    private Venda venda;

    public VendaTestMysql() {
        vendaDAO = new VendaDAOMysql();
        clienteDAO = new ClienteDAOMysql();
        produtoDAO = new ProdutoDAOMysql();
        vendaService = new VendaServiceMysql(vendaDAO);
    }

    @Before
    public void init() {
        cliente = criarCliente(123456789l);
        produto = criarProduto(123l);
        venda = criarVenda(10l);

    }

    @Test
    public void cadastrarTest() {

        assertNotNull(venda);
        assertNotNull(venda.getId());
        assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void buscarTest() {
        Venda vendaBuscada = vendaService.consultar(venda.getId());

        assertNotNull(vendaBuscada);
        assertEquals(vendaBuscada.getId(),venda.getId());

        assertEquals(vendaBuscada.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void adicionarProdutoDiferenteTest() {
        Produto produto2 = produtoDAO.cadastrar(criarProduto(1010l));

        assertTrue(produto.getQuantidade() == 1);

        assertTrue(produto2.getQuantidade() == 3);

        vendaService.adicionarProduto(produto2,2,venda);

        venda = vendaService.consultar(venda.getId());

        assertTrue(vendaService.getQuantidadeTotalProdutos() == 4);

        BigDecimal valorTotal = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_DOWN);

        assertEquals(venda.getValorTotal(),valorTotal);

        assertTrue(produto2.getQuantidade() == 1);

        assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void adicionarProdutoIgualTest() {
        assertTrue(produto.getQuantidade() == 1);

        vendaService.adicionarProduto(produto,1,venda);

        produto = produtoDAO.consultar(produto.getId());

        assertTrue(produto.getQuantidade() == 0);

        venda = vendaService.consultar(venda.getId());

        assertTrue(vendaService.getQuantidadeTotalProdutos() == 3);

        BigDecimal valorTotal = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);

        assertEquals(venda.getValorTotal(),valorTotal);

        assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void removerProdutoTest() {
        vendaService.removerProduto(produto,1,venda);

        venda = vendaService.consultar(venda.getId());

        produto = produtoDAO.consultar(produto.getId());

        assertTrue(produto.getQuantidade() == 2);

        assertTrue(vendaService.getQuantidadeTotalProdutos() == 1);

        BigDecimal valorTotal = BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_DOWN);

        assertEquals(venda.getValorTotal(),valorTotal);

        assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void removerTodosOsProdutosTest() {
        Produto produto2 = produtoDAO.cadastrar(criarProduto(24524l));

        vendaService.adicionarProduto(produto2,2,venda);

        assertTrue(vendaService.getQuantidadeTotalProdutos() == 4);

        venda = vendaService.consultar(venda.getId());

        vendaService.removerTodosProdutos(venda);

        venda = vendaService.consultar(venda.getId());

        assertTrue(vendaService.getQuantidadeTotalProdutos() == 0);

        BigDecimal valorTotal = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_DOWN);

        assertEquals(venda.getValorTotal(),valorTotal);

        assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void removerMaisProdutosDoQuePossui() {
        produto = produtoDAO.consultar(produto.getId());

        assertTrue(produto.getQuantidade() == 1);

        vendaService.removerProduto(produto,3,venda);

        produto = produtoDAO.consultar(produto.getId());

        assertTrue(produto.getQuantidade() == 3);

        venda = vendaService.consultar(venda.getId());

        BigDecimal valorTotal = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_DOWN);

        assertEquals(venda.getValorTotal(),valorTotal);

        assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void alterarVendaFinalizadaTest() {
        vendaService.finalizarVenda(venda);

        assertEquals(venda.getStatus(), Venda.Status.CONCLUIDA);

        vendaService.adicionarProduto(produto,1,venda);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void alterarVendaCanceladaTest() {
        vendaService.cancelarVenda(venda);

        venda = vendaService.consultar(venda.getId());

        assertEquals(venda.getStatus(), Venda.Status.CANCELADA);

        vendaService.adicionarProduto(produto,1,venda);
    }

    @After
    public void excluirTudo() {
        excluirVendas();
        excluirProdutos();
        clienteDAO.excluir(cliente);
    }

    private Cliente criarCliente(long cpf) {
        Cliente cliente = new Cliente();
        cliente.setNome("Felipe");
        cliente.setCpf(cpf);
        cliente.setEndereco("Endereco");
        cliente.setTelefone(11999999999l);
        cliente.setCidade("Sao Paulo");
        cliente.setEstado("SP");
        cliente.setIdade(38);
        return cliente;
    }

    private Produto criarProduto(long codigo) {
        Produto produto = new Produto();
        produto.setNome("Televisao");
        produto.setDescricao("Descricao");
        produto.setCodigo(codigo);
        produto.setQuantidade(3);
        produto.setValor(BigDecimal.TEN);
        return produto;
    }

    private Venda criarVenda(long codigo) {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setDataDaVenda(Instant.now());
        venda.setCliente(cliente);
        venda.setStatus(Venda.Status.INICIADA);
        vendaService.adicionarProduto(produto,2,venda);
        return vendaService.cadastrar(venda);
    }

    private void excluirProdutos() {
        Collection<Produto> produtos = produtoDAO.buscarTodos();
        produtos.forEach(prod ->
                produtoDAO.excluir(prod));
    }

    private void excluirVendas() {
        Collection<Venda> vendas = vendaService.buscarTodos();
        vendas.forEach(vend ->
                vendaService.excluir(vend));
    }
}
