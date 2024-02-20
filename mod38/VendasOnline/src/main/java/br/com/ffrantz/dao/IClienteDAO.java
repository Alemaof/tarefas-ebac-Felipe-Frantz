package br.com.ffrantz.dao;

import java.util.List;

import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.entity.Cliente;

public interface IClienteDAO extends IGenericDAO<Cliente,Long> {
	
	List<Cliente> filtrarClientes(String query);
}
