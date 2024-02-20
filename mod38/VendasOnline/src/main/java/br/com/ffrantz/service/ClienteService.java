package br.com.ffrantz.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ffrantz.dao.IClienteDAO;
import br.com.ffrantz.entity.Cliente;
import br.com.ffrantz.service.generics.GenericService;

@Stateless
public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

	private IClienteDAO clienteDAO;

	@Inject
    public ClienteService(IClienteDAO clienteDAO) {
        super(clienteDAO);
        this.clienteDAO = clienteDAO;
    }

	@Override
	public Cliente buscarPorCPF(Long cpf) {
			return this.clienteDAO.consultar(cpf);
	}

	@Override
	public List<Cliente> filtrarClientes(String query) {
		return clienteDAO.filtrarClientes(query);
	}
	
	
}
