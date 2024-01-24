package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.domain.Cliente;

public interface IClienteDAO {

    public Boolean salvar(Cliente cliente);

    public Cliente buscarPorCchave(Long cpf);

    public void excluir(Long cpf);

    public void alterar(Cliente cliente);
}
