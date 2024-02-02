package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericMysqlDAO;
import br.com.ffrantz.entity.Cliente;

public class ClienteDAOMysql extends GenericMysqlDAO<Cliente, Long> implements IClienteDAO {

    public ClienteDAOMysql() {
        super(Cliente.class);
    }
}
