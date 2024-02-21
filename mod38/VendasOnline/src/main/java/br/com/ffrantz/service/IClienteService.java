package br.com.ffrantz.service;

import java.util.List;

import br.com.ffrantz.entity.Cliente;
import br.com.ffrantz.service.generics.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {
	
	Cliente buscarPorCPF(Long cpf);

	List<Cliente> filtrarClientes(String query);
}
