package br.com.ffrantz.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ffrantz.dao.IProdutoDAO;
import br.com.ffrantz.entity.Produto;
import br.com.ffrantz.service.generics.GenericService;

@Stateless
public class ProdutoService extends GenericService<Produto,Long> implements IProdutoService {

	private IProdutoDAO produtoDAO;
	
    @Inject
    public ProdutoService(IProdutoDAO produtoDAO) {
        super(produtoDAO);
        this.produtoDAO = produtoDAO;
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

	@Override
	public List<Produto> filtrarProdutos(String query) {
		return produtoDAO.filtrarProdutos(query);
	}
}
