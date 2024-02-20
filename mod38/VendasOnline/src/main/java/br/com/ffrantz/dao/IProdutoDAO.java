package br.com.ffrantz.dao;

import java.util.List;

import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.entity.Produto;

public interface IProdutoDAO extends IGenericDAO<Produto, Long> {

	List<Produto> filtrarProdutos(String query);
}
