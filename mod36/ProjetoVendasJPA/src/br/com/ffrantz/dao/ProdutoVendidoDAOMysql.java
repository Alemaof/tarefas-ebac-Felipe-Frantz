package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericMysqlDAO;
import br.com.ffrantz.entity.ProdutoVendido;

public class ProdutoVendidoDAOMysql extends GenericMysqlDAO<ProdutoVendido,Long> implements IProdutoVendidoDAO {

    public ProdutoVendidoDAOMysql() {
        super(ProdutoVendido.class);
    }
}
