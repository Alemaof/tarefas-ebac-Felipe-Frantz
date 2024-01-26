package br.com.ffrantz.dao;

import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.domain.Produto;

import java.sql.SQLException;
import java.util.List;

public interface IProdutoDAO {

    public Integer salvar(Produto produto) throws SQLException;

    public Integer atualizar(Produto produto) throws SQLException;

    public Integer excluir(Produto produto) throws SQLException;

    public Produto buscar(Long codigo) throws SQLException;

    public List<Produto> buscarTodos() throws SQLException;
}
