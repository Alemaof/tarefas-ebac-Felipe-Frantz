package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Cliente;

public class ClienteDAOMock implements IClienteDAO {

    @Override
    public Boolean salvar(Cliente cliente) {
        return true;
    }

    @Override
    public Cliente buscarPorCchave(Long cpf) {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        return cliente;
    }

    @Override
    public void excluir(Long cpf) {

    }

    @Override
    public void alterar(Cliente cliente) {

    }
}
