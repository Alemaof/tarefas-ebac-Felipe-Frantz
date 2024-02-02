package br.com.ffrantz.dao;

import br.com.ffrantz.dao.IProdutoDAO;
import br.com.ffrantz.dao.generic.GenericMysqlDAO;
import br.com.ffrantz.entity.Produto;

public class ProdutoDAOMysql extends GenericMysqlDAO<Produto, Long> implements IProdutoDAO {

    public ProdutoDAOMysql() {
        super(Produto.class);
    }
}
