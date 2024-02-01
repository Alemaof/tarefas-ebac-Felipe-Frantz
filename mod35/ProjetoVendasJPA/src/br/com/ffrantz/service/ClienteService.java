package br.com.ffrantz.service;

import br.com.ffrantz.dao.ClienteDAO;
import br.com.ffrantz.dao.IClienteDAO;
import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.entity.Cliente;
import br.com.ffrantz.service.generics.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {


    public ClienteService(IGenericDAO<Cliente, Long> dao) {
        super(dao);
    }
}
