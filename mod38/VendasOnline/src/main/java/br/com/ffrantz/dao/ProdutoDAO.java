package br.com.ffrantz.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.entity.Produto;

public class ProdutoDAO extends GenericDAO<Produto, Long> implements IProdutoDAO {

    public ProdutoDAO() {
        super(Produto.class);
    }

	@Override
	public List<Produto> filtrarProdutos(String query) {
		TypedQuery<Produto> tpQuery = 
				this.entityManager.createNamedQuery("Produto.findByNome", this.persistenteClass);
		tpQuery.setParameter("nome", "%" + query + "%");
        return tpQuery.getResultList();
	}

}
