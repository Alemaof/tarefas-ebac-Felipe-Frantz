package br.com.ffrantz.dao;

import br.com.ffrantz.domain.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDAO {

    public Integer salvar(Cliente cliente) throws SQLException;

    public Integer atualizar(Cliente cliente) throws SQLException;

    public Integer excluir(Cliente cliente) throws SQLException;

    public Cliente buscar(Long cpf) throws SQLException;

    public List<Cliente> buscarTodos() throws SQLException;
}
