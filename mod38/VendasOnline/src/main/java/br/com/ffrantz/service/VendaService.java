package br.com.ffrantz.service;

import br.com.ffrantz.dao.*;
import br.com.ffrantz.entity.Venda;
import br.com.ffrantz.entity.Venda.Status;
import br.com.ffrantz.service.generics.GenericService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VendaService extends GenericService<Venda,Long> implements IVendaService {

    private IVendaDAO vendaDAO;

    @Inject
    private IProdutoService produtoService;

    @Inject
    public VendaService(IVendaDAO vendaDAO) {
        super(vendaDAO);
        this.vendaDAO = vendaDAO;
        new ProdutoVendidoDAO();

    }
    
    @Override
    public Venda cadastrar(Venda venda) {
    	venda.setStatus(Status.INICIADA);
		return super.cadastrar(venda);
    }

    private void validarStatus(Venda venda){
        if(venda.getId() != null) {
            Venda vendadb = consultar(venda.getId());
            if (vendadb.getStatus() != Venda.Status.INICIADA) {
                throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
            }
        }
    }

    @Override
    public void finalizarVenda(Venda venda) {
    	validarStatus(venda);
        venda.setStatus(Venda.Status.CONCLUIDA);
        vendaDAO.alterar(venda);
        atualizarEstoque(venda);
    }

    private void atualizarEstoque(Venda venda) {
    	venda = consultar(venda.getId());
    	venda.getProdutoVendido().forEach(prod -> 
    		produtoService.removerEstoque(prod.getProduto(), prod.getQuantidade())
    	);		
	}

	@Override
    public void cancelarVenda(Venda venda) {
    	validarStatus(venda);
        venda.setStatus(Venda.Status.CANCELADA);
        vendaDAO.alterar(venda);
    }

    @Override
    public void recalcularValorTotalVenda(Venda venda) {
		vendaDAO.recalcularValorTotalVenda(venda);
	}

    @Override
    public Venda alterar(Venda venda) {
        validarStatus(venda);
        return vendaDAO.alterar(venda);
    }

	@Override
	public Venda consultarComCollection(Long id) {
		return vendaDAO.consultarComCollection(id);
	}
}
