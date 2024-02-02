package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericPostgreDAO;
import br.com.ffrantz.entity.Cliente;

public class ClienteDAOPostgre extends GenericPostgreDAO<Cliente, Long> implements IClienteDAO {

    public ClienteDAOPostgre() {
        super(Cliente.class);
    }

}
