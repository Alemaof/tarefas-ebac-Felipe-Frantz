package br.com.ffrantz.service;

import br.com.ffrantz.dao.ClienteDAO;
import br.com.ffrantz.dao.IClienteDAO;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.exceptions.TipoChaveNaoEncontradaException;

public class ClienteService extends GenericService {

    public ClienteService(GenericDAO clienteDAO) {
        super(clienteDAO);
    }
}
