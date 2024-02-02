package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericPostgreDAO;
import br.com.ffrantz.entity.ProdutoVendido;

public class ProdutoVendidoDAOPostgre extends GenericPostgreDAO<ProdutoVendido, Long> implements IProdutoVendidoDAO {

    public ProdutoVendidoDAOPostgre() {
        super(ProdutoVendido.class);
    }

}
