package br.com.ffrantz.dao.generic;

import br.com.ffrantz.domain.Persistente;

import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T extends Persistente, E extends Serializable> {

    public Integer salvar(T entity) throws SQLException;

    public Integer atualizar(T entity) throws SQLException;

    public Integer excluir(T entity) throws SQLException;

    public T buscar(E codigo) throws SQLException;

    public List<T> buscarTodos() throws SQLException;
}
