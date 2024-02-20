package br.com.ffrantz.service;

import br.com.ffrantz.entity.Venda;
import br.com.ffrantz.service.generics.IGenericService;

public interface IVendaService extends IGenericService<Venda,Long> {

    public void finalizarVenda(Venda venda);

    public void cancelarVenda(Venda venda);

    //public Integer getQuantidadeTotalProdutos();
    
    public Venda consultarComCollection(Long id);
    
    public void recalcularValorTotalVenda(Venda venda);
    
    public Venda cadastrar(Venda venda);
}
