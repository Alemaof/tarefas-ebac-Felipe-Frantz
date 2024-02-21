package br.com.ffrantz.service;

import java.util.List;

import br.com.ffrantz.entity.Produto;
import br.com.ffrantz.service.generics.IGenericService;

public interface IProdutoService extends IGenericService<Produto,Long> {

    public void adicionarEstoque(Produto produto, Integer quantidade);

    public void removerEstoque(Produto produto, Integer quantidade);

	public List<Produto> filtrarProdutos(String query);
}
