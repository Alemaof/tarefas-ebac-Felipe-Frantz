package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.entity.Venda;

public interface IVendaDAO extends IGenericDAO<Venda, Long> {

    public Venda consultarComCollection(Long id);

    public void recalcularValorTotalVenda(Venda venda);

}
