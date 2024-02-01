package br.com.ffrantz.service;

import br.com.ffrantz.dao.IProdutoDAO;
import br.com.ffrantz.dao.ProdutoDAO;
import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.entity.Produto;
import br.com.ffrantz.service.generics.GenericService;

public class ProdutoService extends GenericService<Produto,Long> implements IProdutoService {

    private IProdutoDAO produtoDAO;
    public ProdutoService(IProdutoDAO produtoDAO) {
        super(produtoDAO);
    }

    @Override
    public void adicionarEstoque(Produto produto, Integer quantidade) {
        produto.setQuantidade(produto.getQuantidade()+quantidade);
        dao.alterar(produto);
    }

    @Override
    public void removerEstoque(Produto produto, Integer quantidade) {
        produto.setQuantidade(produto.getQuantidade()-quantidade);
        dao.alterar(produto);
    }
}
