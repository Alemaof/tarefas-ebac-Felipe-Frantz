package br.com.ffrantz.service;

import br.com.ffrantz.entity.Produto;
import br.com.ffrantz.entity.Venda;
import br.com.ffrantz.service.generics.IGenericService;

public interface IVendaService extends IGenericService<Venda,Long> {

    public void finalizarVenda(Venda venda);

    public void cancelarVenda(Venda venda);

    public void adicionarProduto(Produto produto, Integer quantidade, Venda venda);

    public void removerProduto(Produto produto, Integer quantidade, Venda venda);

    public void removerTodosProdutos(Venda venda);

    public Integer getQuantidadeTotalProdutos();
}
