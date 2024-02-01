package br.com.ffrantz.service;

import br.com.ffrantz.dao.IProdutoDAO;
import br.com.ffrantz.dao.IVendaDAO;
import br.com.ffrantz.dao.ProdutoDAO;
import br.com.ffrantz.dao.VendaDAO;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.entity.Produto;
import br.com.ffrantz.entity.ProdutoVendido;
import br.com.ffrantz.entity.Venda;
import br.com.ffrantz.service.generics.GenericService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VendaService extends GenericService<Venda,Long> implements IVendaService {

    private IVendaDAO vendaDAO;

    private Set<ProdutoVendido> produtos = new HashSet<>();

    private IProdutoService produtoService;

    private IProdutoDAO produtoDAO;


    public VendaService(IVendaDAO vendaDAO) {
        super(vendaDAO);
        this.produtoDAO = new ProdutoDAO();
        this.produtoService = new ProdutoService(produtoDAO);
        this.vendaDAO = new VendaDAO();

    }

    private void validarStatus(Venda venda){
        if (venda.getStatus() != Venda.Status.INICIADA) {
            throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
        }
    }

    @Override
    public void finalizarVenda(Venda venda) {
        venda.setStatus(Venda.Status.CONCLUIDA);
        vendaDAO.alterar(venda);
    }

    @Override
    public void cancelarVenda(Venda venda) {
        venda.setStatus(Venda.Status.CANCELADA);
        vendaDAO.alterar(venda);
    }

    @Override
    public void adicionarProduto(Produto produto, Integer quantidade, Venda venda) {
        validarStatus(venda);
        Optional<ProdutoVendido> op =
                produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();
        if (op.isPresent()) {
            ProdutoVendido produtoQtd = op.get();
            produtoQtd.setQuantidade(produtoQtd.getQuantidade() + quantidade);
            produtoQtd.setValorTotal(produto.getValor().multiply(BigDecimal.valueOf(produtoQtd.getQuantidade())));
            produtos.remove(produtoQtd);
            produtos.add(produtoQtd);
            produtoService.removerEstoque(produtoQtd.getProduto(),quantidade);
        } else {
            ProdutoVendido prod = new ProdutoVendido();
            prod.setVenda(venda);
            prod.setProduto(produto);
            prod.setQuantidade(quantidade);
            prod.setValorTotal(produto.getValor().multiply(BigDecimal.valueOf(quantidade)));
            produtos.add(prod);
            produtoService.removerEstoque(produto,quantidade);
        }
        venda.setProdutoVendido(produtos);
        vendaDAO.recalcularValorTotalVenda(venda);
        if(venda.getId()!=null) {
            vendaDAO.alterar(venda);
        }
    }

    @Override
    public void removerProduto(Produto produto, Integer quantidade, Venda venda) {
        validarStatus(venda);
        Optional<ProdutoVendido> op =
                produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();
        if (op.isPresent()) {
            ProdutoVendido produtoQtd = op.get();
            if (produtoQtd.getQuantidade() > quantidade) {
                produtoQtd.setQuantidade(produtoQtd.getQuantidade() - quantidade);
                produtoQtd.setValorTotal(produto.getValor().multiply(BigDecimal.valueOf(produtoQtd.getQuantidade())));
                produtos.remove(produtoQtd);
                produtos.add(produtoQtd);
                produtoService.adicionarEstoque(produtoQtd.getProduto(), quantidade);
            } else {
                produtoService.adicionarEstoque(produtoQtd.getProduto(), produtoQtd.getQuantidade());
                produtos.remove(produtoQtd);
            }

            venda.setProdutoVendido(produtos);
            vendaDAO.recalcularValorTotalVenda(venda);
            vendaDAO.alterar(venda);
        }
    }

    @Override
    public void removerTodosProdutos(Venda venda) {
        produtos.forEach(prod ->
                produtoService.adicionarEstoque(prod.getProduto(), prod.getQuantidade()));
        produtos.clear();
        venda.setValorTotal(BigDecimal.ZERO);
        vendaDAO.alterar(venda);
    }

    @Override
    public Integer getQuantidadeTotalProdutos() {
        int result = produtos.stream()
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantidade(), Integer::sum);
        return result;
    }
}
