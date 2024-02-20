package br.com.ffrantz.service;


import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ffrantz.dao.IProdutoVendidoDAO;
import br.com.ffrantz.entity.ProdutoVendido;

import br.com.ffrantz.service.generics.GenericService;

@Stateless
public class ProdutoVendidoService extends GenericService<ProdutoVendido,Long>implements IProdutoVendidoService {
	
	private IProdutoVendidoDAO produtoVendidoDAO;
	@Inject
	public ProdutoVendidoService(IProdutoVendidoDAO produtoVendidoDAO) {
		super(produtoVendidoDAO);
		this.produtoVendidoDAO = produtoVendidoDAO;
	}

 
}
