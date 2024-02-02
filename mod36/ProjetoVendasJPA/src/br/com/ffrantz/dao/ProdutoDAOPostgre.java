package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericPostgreDAO;
import br.com.ffrantz.entity.Produto;

public class ProdutoDAOPostgre extends GenericPostgreDAO<Produto, Long> implements IProdutoDAO {

    public ProdutoDAOPostgre() {
        super(Produto.class);
    }

}
