package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.entity.ProdutoVendido;

public class ProdutoVendidoDAO extends GenericDAO<ProdutoVendido, Long> implements IProdutoVendidoDAO {

    public ProdutoVendidoDAO() {
        super(ProdutoVendido.class);
    }

}
